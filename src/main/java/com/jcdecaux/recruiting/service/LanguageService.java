package com.jcdecaux.recruiting.service;

import com.jcdecaux.recruiting.domain.Language;
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
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Long create(Language language) {
        Language savedLanguage = languageRepository.save(language);
        return savedLanguage.getId();
    }

    public List<Language> findAll() {
        return languageRepository.findAll();
    }
}
