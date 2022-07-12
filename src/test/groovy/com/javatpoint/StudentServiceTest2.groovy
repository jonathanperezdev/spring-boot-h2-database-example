package com.javatpoint

import com.javatpoint.model.Student
import com.javatpoint.repository.StudentRepository
import com.javatpoint.service.StudentService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

//Argument capture example, how get the parameter received for a Mocked class, in this case repository.save
@SpringBootTest(classes = [SpringBootH2DatabaseExampleApplication.class])
class StudentServiceTest2 extends Specification {

    @Autowired
    private StudentService service

    @SpringBean
    private StudentRepository repository = Mock(StudentRepository.class)

    //Capturing the parameter of repository.save and assign to variable to validate
    def "Given an student When save Then Student repo save entity should match id"(){
        //Declare external variable to validate
        Student entity
        given: "an student"
            Student student = new Student()
            student.setName("testName")
            student.setId(1)

        when: "save"
            service.saveOrUpdate(student)

        then: "repo save entity should match id"
            //Assign the parameter to the external variable
            1 * repository.save(_) >> {arguments -> entity = arguments[0]}

            //Validating
            entity.getId() == 1
    }

    //Validating the parameter of repository.save in line
    def "Given an student persisted in DB When get student by id Then Student id should match name"(){
        given: "an student"
            Student student = new Student()
            student.setName("testName")
            student.setId(1)

        when: "save"
            service.saveOrUpdate(student)

        then: "Student name should match"
            //The entity received in repository is validated here
            1 * repository.save({Student entity2 -> entity2.getName().is("testName")})
    }
}

