package com.jcdecaux.recruiting.service;

import com.jcdecaux.recruiting.domain.Developer;
import com.jcdecaux.recruiting.domain.Language;
import com.jcdecaux.recruiting.repository.DeveloperRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by nbenattia on 22/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class DeveloperServiceTest{

    @MockBean
    private DeveloperRepository developerRepository;

    @Autowired
    private DeveloperService developerService;

    Developer developer = new Developer();
    Language language =new Language();

    @Test
    public void should_return_created_developer() {
        // Arrange
        language.setName("Java");
        ArrayList<Language> languages = new ArrayList<Language>();
        languages.add(language);
        developer.setFirstName("first name");
        developer.setLastName("last name");
        developer.setLanguages(languages);
        given(developerRepository.save(developer)).willReturn(developer);
        // Act
        Long id = developerService.create(developer);
        //Assert
        assertThat(id == 1);
    }


    @Test
    public void should_return_updated_developer() {
        // Arrange
        given(developerRepository.findOne(1L)).willReturn(developer);
        developer.setFirstName("first name updated");
        developer.setLastName("last name updated");
        given(developerRepository.save(developer)).willReturn(developer);
        // Act
        developerService.update(1L,developer);
        //Assert
        assertThat(developer.getId() == 1L);
        assertThat(developer.getFirstName() == "first name updated");
        assertThat(developer.getLastName() == "last name updated");
    }


}
