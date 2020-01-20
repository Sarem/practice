package com.snap.practice.github;

import com.snap.practice.contact.ContactService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class GithubComponentTest {

    @Autowired
    private GithubComponent githubComponent;

    @Test
    public void getExistRepositoriesTest(){
        Assertions.assertDoesNotThrow(() -> {
            githubComponent.getUserRepositories("Sarem").stream().forEach(repository -> System.out.println(repository));
        });
    }


    @Test
    public void getNotExistRepositoriesTest(){
        Assertions.assertDoesNotThrow(() -> {
            githubComponent.getUserRepositories("asdsadasfsavb").stream().forEach(repository -> System.out.println(repository));
        });
    }

}
