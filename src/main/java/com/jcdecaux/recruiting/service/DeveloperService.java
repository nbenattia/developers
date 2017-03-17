package com.jcdecaux.recruiting.service;

import com.jcdecaux.recruiting.domain.Developer;
import com.jcdecaux.recruiting.domain.Language;
import com.jcdecaux.recruiting.repository.DeveloperRepository;
import com.jcdecaux.recruiting.repository.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nbenattia on 17/03/2017.
 */

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public Long create(Developer developer) {
        Developer savedDeveloper = developerRepository.save(developer);
        return savedDeveloper.getId();
    }

    public Developer update(Long id, Developer developer) {
        Developer currentDeveloper = developerRepository.findOne(id);
        if (currentDeveloper == null) {
            throw new IllegalArgumentException("Unknown developer[" + id + "]");
        }
        currentDeveloper.setFirstName(developer.getFirstName());
        currentDeveloper.setLastName(developer.getLastName());
        Developer updatedDeveloper = developerRepository.save(currentDeveloper);
        return updatedDeveloper;
    }

    public Developer addLanguage(Long id, Language language) {
        Developer currentDeveloper = developerRepository.findOne(id);
        if (currentDeveloper == null) {
            throw new IllegalArgumentException("Unknown developer[" + id + "]");
        }

        Language currentLanguage = languageRepository.findOne(language.getId());
        if (currentLanguage == null) {
            throw new IllegalArgumentException("Unknown language[" + language.getId() + "]");
        }

        currentDeveloper.getLanguages().add(currentLanguage);
        Developer updatedDeveloper = developerRepository.save(currentDeveloper);
        return updatedDeveloper;
    }

    public List<Developer> findAll(String languageName) {
        if (languageName == null) {
            return developerRepository.findAll();
        } else {
            return developerRepository.findAllByLanguagesName(languageName);
        }
    }
}
