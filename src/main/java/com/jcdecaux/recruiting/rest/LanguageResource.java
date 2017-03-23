package com.jcdecaux.recruiting.rest;

import com.jcdecaux.recruiting.domain.Language;
import com.jcdecaux.recruiting.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by nbenattia on 17/03/2017.
 */
@RestController
@RequestMapping("/languages")
public class LanguageResource {

    private static Logger logger = LoggerFactory.getLogger(LanguageResource.class);

    @Autowired
    private LanguageService languageService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createLanguage(@RequestBody Language language, UriComponentsBuilder uriComponentsBuilder) {
        Long id = languageService.create(language);
        logger.info("Language created");
        URI uri = uriComponentsBuilder.path("/languages/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLanguages() {
        List<Language> languages = languageService.findAll();
        logger.info("Languages fetched");
        return ResponseEntity.ok(languages);
    }


}
