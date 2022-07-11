package com.javatpoint

import com.javatpoint.model.Student
import com.javatpoint.repository.StudentRepository
import com.javatpoint.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

//Integration test Spock
@SpringBootTest(classes = [SpringBootH2DatabaseExampleApplication.class])
class StudentServiceTest extends Specification {

    @Autowired
    private StudentService service

    @Autowired
    private StudentRepository repository

    def "Given an student persisted in DB When get student by id Then Student id should match"(){
        given: "an student persisted in DB"
            repository.save([
                    id: 1,
                    name: "testName"
            ] as Student)

        when: "get student by id"
            Student student = service.getStudentById(1)

        then: "Student id should match"
            student.id.is(1)
    }
}

