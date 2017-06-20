package top.wisely.springdatadomainevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wisely.springdatadomainevent.domain.Person;
import top.wisely.springdatadomainevent.repository.PersonRepository;

/**
 * Created by wangyunfei on 2017/6/19.
 */
@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @PostMapping
    public ResponseEntity savePerson(@RequestBody Person person){
//        personRepository.save(person.afterPersonSavedCompleted());
        personRepository.save(person);
        return new ResponseEntity(HttpStatus.OK);

    }
}
