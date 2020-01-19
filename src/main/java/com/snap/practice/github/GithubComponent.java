package com.snap.practice.github;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class GithubComponent {

    private static final String GITHUB_API_URL = "https://api.github.com";

    private static final String GET_USER_REPOSITORIES = GITHUB_API_URL + "/users/{username}/repos";

    private final RestTemplate restTemplate;

    public GithubComponent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RepositoryModel> getUserRepositories(final String username) {
        Objects.requireNonNull(username);
        List<RepositoryModel> repositories=new ArrayList<>();
        try {
            final ResponseEntity<List<RepositoryModel>> response = restTemplate.exchange(
                    GET_USER_REPOSITORIES,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<RepositoryModel>>(){},
                    username);
            repositories.addAll(response.getBody()) ;

        }catch (Exception e){
            //TODO logger
        }
        return repositories;
    }
}
