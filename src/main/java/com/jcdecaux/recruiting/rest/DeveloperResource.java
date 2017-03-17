package com.jcdecaux.recruiting.rest;

import com.jcdecaux.recruiting.domain.Developer;
import com.jcdecaux.recruiting.domain.Language;
import com.jcdecaux.recruiting.service.DeveloperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


/**
 * Created by nbenattia on 17/03/2017.
 */
@RequestMapping("/developers")
@RestController
public class DeveloperResource {

    private static Logger logger = LoggerFactory.getLogger(DeveloperResource.class);

    @Autowired
    private DeveloperService developerService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDeveloper(@RequestBody Developer developer, UriComponentsBuilder uriComponentsBuilder) {
        Long id = developerService.create(developer);
        logger.info("Developer[{}] created", id);
        URI uri = uriComponentsBuilder.path("/developers/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDeveloper(@PathVariable("id") Long id, @RequestBody Developer developer) {
        Developer updatedDeveloper = developerService.update(id, developer);
        logger.info("Developer[{}] updated", id);
        return ResponseEntity.ok(updatedDeveloper);
    }

    @RequestMapping(value = "/{id}/languages", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addLanguage(@PathVariable("id") Long id, @RequestBody Language language) {
        Developer updatedDeveloper = developerService.addLanguage(id, language);
        logger.info("Developer[{}] updated with language[{}]", id, language.getName());
        return ResponseEntity.ok(updatedDeveloper);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDeveloper(@RequestParam(value = "language_name", required = false) String languageName) {
        List<Developer> developers = developerService.findAll(languageName);
        logger.info("Developers fetched");
        return ResponseEntity.ok(developers);
    }

}
