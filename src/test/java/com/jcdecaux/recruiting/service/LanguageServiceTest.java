package com.jcdecaux.recruiting.service;

import com.jcdecaux.recruiting.domain.Language;
import com.jcdecaux.recruiting.repository.LanguageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by nbenattia on 22/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class LanguageServiceTest {
    @MockBean
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageService languageService;

    Language language = new Language();


    @Test
    public void should_create_language() {
        // Arrange
        language.setId(1L);
        language.setName("Java");
        given(languageRepository.save(language)).willReturn(language);
        // Act
        languageService.create(language);
        //Assert
        assertThat(language.getName() == "Java");


    }

}
