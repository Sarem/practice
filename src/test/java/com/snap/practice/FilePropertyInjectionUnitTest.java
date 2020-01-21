package com.snap.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)

public class FilePropertyInjectionUnitTest {

    @Value("${properties.foo}")
    private String gitUrl;

    @Test
    public void whenFilePropertyProvided_thenProperlyInjected() {
        assertThat(gitUrl).isEqualTo("bar");
    }
}
