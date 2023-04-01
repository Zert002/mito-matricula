package com.mitocode.controller;

import com.mitocode.dto.StudentDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Student;
import com.mitocode.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService service;
    @Qualifier(value = "studentMapper")
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception{
        List<StudentDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Student obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.readById(dto.getIdStudent());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdStudent());
        }

        Student stud = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(stud), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Student obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /*Other methods*/
    @GetMapping("/order")
    public ResponseEntity<List<StudentDTO>> readAllOrderDesc() throws Exception{
        List<StudentDTO> list = service.listAllByAgeOrderDesc().stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    /*Other Methods*/
    private StudentDTO convertToDto(Student obj) {
        return mapper.map(obj, StudentDTO.class);
    }
    private Student convertToEntity(StudentDTO dto){
        return mapper.map(dto, Student.class);
    }
}
