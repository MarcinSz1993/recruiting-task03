package com.marcinsz.recruitingtask03.integration;

import com.marcinsz.recruitingtask03.model.Branch;
import com.marcinsz.recruitingtask03.model.ExpectedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ApiIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Test
    void shouldGetUserReposAndBranchesSuccessfully() {
        // GIVEN
        String username = "MarcinSz1993";
        String uri = "/api/solution?username=" + username;

        // WHEN AND THEN
        webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ExpectedResponse.class)
                .value(expectedResponses -> {
                    assertThat(expectedResponses).isNotEmpty();

                    ExpectedResponse firstResponse = expectedResponses.getFirst();
                    assertThat(firstResponse.getRepositoryName()).isNotEmpty();
                    assertThat(firstResponse.getRepositoryName()).isEqualTo("bankservice");
                    assertThat(firstResponse.getOwnerLogin()).isEqualTo(username);

                    assertThat(firstResponse.getBranchList()).isNotEmpty();
                    assertThat(firstResponse.getBranchList()).hasSize(1);

                    Branch firstBranch = firstResponse.getBranchList().getFirst();
                    assertThat(firstBranch.getName()).isNotEmpty();
                    assertThat(firstBranch.getName()).isEqualTo("master");
                });
    }
}
