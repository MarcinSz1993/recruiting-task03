package com.marcinsz.recruitingtask03.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfig {

    @Value("${spring.git.api.urlToGetRepositoriesForUser}")
    private String urlToGetRepositoriesForUser;

    @Value("${spring.git.api.urlToGetBranchesFromUserRepo}")
    private String urlToGetBranchesFromUserRepo;

    @Value("${spring.git.api.urlToGetUserByUsername}")
    private String urlToGetUserByUsername;

    @Value("${spring.git.access-token}")
    private String gitHubToken;

    @Bean(name = "getReposForUserWebClient")
    public WebClient getReposForUserWebClient() {
        return WebClient.builder()
                .baseUrl(urlToGetRepositoriesForUser)
                .defaultHeader(HttpHeaders.AUTHORIZATION, setToken())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean(name = "getBranchesFromUserRepoWebClient")
    public WebClient getBranchesFromUserRepoWebClient(){
        return WebClient.builder()
                .baseUrl(urlToGetBranchesFromUserRepo)
                .defaultHeader(HttpHeaders.AUTHORIZATION, setToken())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean(name = "getUserFromUsernameWebClient")
    public WebClient getUserFromUsernameWebClient(){
        return WebClient.builder()
                .baseUrl(urlToGetUserByUsername)
                .defaultHeader(HttpHeaders.AUTHORIZATION, setToken())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private String setToken() {
        return "Bearer " + gitHubToken;
    }
}
