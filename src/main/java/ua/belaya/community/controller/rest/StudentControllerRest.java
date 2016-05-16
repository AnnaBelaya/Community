package ua.belaya.community.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.belaya.community.domain.Student;
import ua.belaya.community.domain.StudentDTO;
import ua.belaya.community.repository.StudentJpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anna Belaya
 */

@RestController
@RequestMapping("/rest")
public class StudentControllerRest {
    @Autowired
    private StudentJpaRepository studentRepository;

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        try {
            studentRepository.save(student);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDTO>> studentList() {
        List<StudentDTO> students = new ArrayList<>();

        for (Student student : studentRepository.findAll()) {
            students.add(new StudentDTO(student));
        }

        if (!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "students/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id) {
        Student student = studentRepository.findOne(id);

        if (student != null) {
            return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "students/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {
        Student dbStudent = studentRepository.findOne(id);

        if (dbStudent != null) {
            try {
                student.setId(dbStudent.getId());
                studentRepository.save(student);

                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "students/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        Student student = studentRepository.findOne(id);

        if (student != null) {
            studentRepository.delete(student);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
