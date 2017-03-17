package com.jcdecaux.recruiting.repository;

import com.jcdecaux.recruiting.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by nbenattia on 17/03/2017.
 */
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    List<Developer> findAllByLanguagesName(String languageName);

}
