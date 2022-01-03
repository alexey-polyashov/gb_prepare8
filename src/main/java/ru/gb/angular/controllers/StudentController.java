package ru.gb.angular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.gb.angular.models.Student;
import ru.gb.angular.repo.StudentRepo;

import java.util.List;

@RequestMapping("/students")
@RestController()
public class StudentController {

    private StudentRepo studentRepo;

    @Autowired
    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("/all")
    public List<Student> findAll(){
        return studentRepo.findAll();
    }

    @GetMapping("/{id}/id")
    public Student findById(@PathVariable(name="id") Long id){
        return studentRepo.findById(id).orElseThrow(()->new ResorceNotFound("Student not found"));
    }

    @PostMapping
    @Transactional
    public Student createStudent(@RequestBody Student student){
        if(student.getId()!=null){
            throw new BadRequestException("Id is not empty");
        }
        return studentRepo.save(student);
    }

    @PutMapping
    @Transactional
    public Student updateStudent(@RequestBody Student student){
        if(student.getId()==null){
            throw new BadRequestException("Id is empty");
        }
        return studentRepo.save(student);
    }

    @DeleteMapping("/{id}/id")
    @Transactional
    public void deletStudent(@PathVariable(name="id") Long id){
        studentRepo.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundException(ResorceNotFound e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleBadRequestException(BadRequestException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
