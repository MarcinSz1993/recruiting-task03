package com.marcinsz.recruitingtask03.service;

import com.marcinsz.recruitingtask03.exception.GitHubUserNotExistException;
import com.marcinsz.recruitingtask03.model.Branch;
import com.marcinsz.recruitingtask03.model.ExpectedResponse;
import com.marcinsz.recruitingtask03.model.Repo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {
    private final WebClient getUserFromUsernameWebClient;
    private final WebClient getReposForUserWebClient;
    private final WebClient getBranchesFromUserRepoWebClient;

    public ApiService(
            @Qualifier("getUserFromUsernameWebClient") WebClient getUserFromUsernameWebClient,
            @Qualifier("getReposForUserWebClient") WebClient getReposForUserWebClient,
            @Qualifier("getBranchesFromUserRepoWebClient") WebClient getBranchesFromUserRepoWebClient)
    {
        this.getUserFromUsernameWebClient = getUserFromUsernameWebClient;
        this.getReposForUserWebClient = getReposForUserWebClient;
        this.getBranchesFromUserRepoWebClient = getBranchesFromUserRepoWebClient;

    }

    public List<ExpectedResponse> getAllRequiredInfo(String username){
        List<ExpectedResponse> responses = new ArrayList<>();
        List<Repo> reposForUser = getReposForUser(username);
        for (Repo repo : reposForUser) {
            ExpectedResponse expectedResponse = ExpectedResponse.builder()
                    .repositoryName(repo.getRepositoryName())
                    .ownerLogin(repo.getOwner().getOwnerLogin())
                    .branchList(getBranchesAndLastCommitShaFromUserRepo(username,repo.getRepositoryName()))
                    .build();
            responses.add(expectedResponse);
        }
        return responses;
    }

    private List<Repo> getReposForUser(String username) {
        if (!isUserExists(username)){
            throw new GitHubUserNotExistException(username);
        }
        return getReposForUserWebClient.get()
                .uri(username + "/repos")
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.NOT_FOUND),clientResponse ->
                        Mono.error(new GitHubUserNotExistException(username)))
                .bodyToFlux(Repo.class)
                .filter(repo -> !repo.isFork())
                .collectList()
                .block();
    }

    private List<Branch> getBranchesAndLastCommitShaFromUserRepo(String username,String repoName){
        return getBranchesFromUserRepoWebClient.get()
                .uri(username+"/" + repoName + "/branches")
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .block();
    }

    private boolean isUserExists(String username) {
        getUserFromUsernameWebClient.get()
                .uri(username)
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.NOT_FOUND),
                        clientResponse -> Mono.error(new GitHubUserNotExistException(username)))
                .bodyToMono(String.class)
                .block();
        return true;
    }
}
