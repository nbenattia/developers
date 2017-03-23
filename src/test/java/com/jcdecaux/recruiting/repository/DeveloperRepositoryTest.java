package com.jcdecaux.recruiting.repository;


import com.jcdecaux.recruiting.domain.Developer;
import com.jcdecaux.recruiting.domain.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by nbenattia on 22/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@DataJpaTest
public class DeveloperRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DeveloperRepository repository;

    Developer developer = new Developer();
    Language language = new Language();

    @Test
    public void should_return_developers() {
        // Arrange
        language.setName("Java");
        ArrayList<Language> languages = new ArrayList<Language>();
        languages.add(language);
        developer.setFirstName("first name");
        developer.setLastName("last name");
        developer.setLanguages(languages);
        testEntityManager.persist(developer);
        // Act
        List<Developer> developers = repository.findAllByLanguagesName("Java");
        // Assert
        assertThat(developers).hasSize(1);
        assertThat(developers.get(0).getFirstName()).isEqualTo("first name");
        assertThat(developers.get(0).getLastName()).isEqualTo("last name");
    }

}
