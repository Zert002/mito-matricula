package com.mitocode.controller;

import com.mitocode.dto.CourseDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Course;
import com.mitocode.service.ICourseService;
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
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService service;
    @Qualifier(value = "courseMapper")
    private final ModelMapper mapper;

    /*@GetMapping
    public ResponseEntity<List<Course>> readAll() throws Exception{
        List<Course> list = service.readAll().stream().toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }*/
    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll() throws Exception {
        List<CourseDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Course obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto) throws Exception{
        Course obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO dto) throws Exception{
        Course obj = service.readById(dto.getIdCourse());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdCourse());
        }

        Course cour = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(cour), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Course obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /*Other Methods*/
    private CourseDTO convertToDto(Course obj) {
        return mapper.map(obj, CourseDTO.class);
    }
    private Course convertToEntity(CourseDTO dto){
        return mapper.map(dto, Course.class);
    }
}
