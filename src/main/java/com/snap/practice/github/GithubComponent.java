package com.snap.practice.github;

//import lombok.extern.slf4j.Slf4j;
import com.snap.practice.github.model.RepositoryModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;


//@Slf4j
@Component
public class GithubComponent {

     private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(GithubComponent.class);

    private static final String GITHUB_API_URL = "https://api.github.com";

    private static final String GET_USER_REPOSITORIES = GITHUB_API_URL + "/users/{username}/repos";

    private final RestTemplate restTemplate;

    public GithubComponent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Set<RepositoryModel> getUserRepositories(final String username) {
        Objects.requireNonNull(username);
        Set<RepositoryModel> repositories=new HashSet<>();
        try {
            final ResponseEntity<Set<RepositoryModel>> response = restTemplate.exchange(
                    GET_USER_REPOSITORIES,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Set<RepositoryModel>>(){},
                    username);
            repositories.addAll(response.getBody()) ;

        }catch (Exception e){
            log.error("getUserRepositories",e);
        }
        return repositories;
    }
}
