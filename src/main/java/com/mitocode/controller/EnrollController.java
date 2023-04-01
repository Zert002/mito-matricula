package com.mitocode.controller;

import com.mitocode.dto.CourseStudentDTO;
import com.mitocode.dto.EnrollDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Enroll;
import com.mitocode.service.IEnrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrolments")
@RequiredArgsConstructor
public class EnrollController {
    private final IEnrollService service;
    @GetMapping
    public ResponseEntity<List<EnrollDTO>> readAll() throws Exception{
        List<EnrollDTO> list = service.readAll().stream().map(service::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnrollDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Enroll obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(service.convertToDto(obj), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EnrollDTO> create(@Valid @RequestBody EnrollDTO dto) throws Exception{
        Enroll obj = service.save(service.convertToEntity(dto));
        return new ResponseEntity<>(service.convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnrollDTO> update(@Valid @RequestBody EnrollDTO dto) throws Exception{
        Enroll obj = service.readById(dto.getIdEnroll());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdEnroll());
        }

        Enroll enr = service.update(service.convertToEntity(dto));
        return new ResponseEntity<>(service.convertToDto(enr), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Enroll obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /*Other Methods*/
    @GetMapping("/studentsbycourse")
    public ResponseEntity<List<CourseStudentDTO>> studentsByCourse() throws Exception{
        List<CourseStudentDTO> obj = service.listStudentsByCourse();

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
