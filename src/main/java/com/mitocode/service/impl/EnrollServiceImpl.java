package com.mitocode.service.impl;

import com.mitocode.dto.*;
import com.mitocode.model.Course;
import com.mitocode.model.Enroll;
import com.mitocode.model.EnrollDetail;
import com.mitocode.model.Student;
import com.mitocode.repo.IEnrollRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IEnrollService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EnrollServiceImpl extends CRUDImpl<Enroll, Integer> implements IEnrollService {
    private final IEnrollRepo repo;
    @Override
    protected IGenericRepo<Enroll, Integer> getRepo() {
        return repo;
    }
    /*Mappers*/
    @Qualifier(value = "enrollMapper")
    private final ModelMapper mapper;

    @Qualifier(value = "studentMapper")
    private final ModelMapper studentMapper;
    @Qualifier(value = "courseMapper")
    private final ModelMapper courseMapper;

    /*Other Functions*/
    @Override
    public List<CourseStudentDTO> listStudentsByCourse() {
        var result = repo.findAll().stream().map(
                        Enroll::getDetails
                )
                .flatMap(Collection::stream).collect(
                        Collectors.groupingBy(
                                EnrollDetail::getCourse
                                , Collectors.mapping(
                                        e -> e.getEnroll().getStudent(), Collectors.toList())))
                .entrySet().stream().map(
                        e -> new CourseStudentDTO(courseConvertToDto(e.getKey()), studentsConvertToDto(e.getValue())))
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
    /*Mapper Functions*/
    private List<StudentDTO> studentsConvertToDto(List<Student> obj) {
        return obj.stream().map(e -> studentMapper.map(e, StudentDTO.class)).collect(Collectors.toCollection(ArrayList::new));
    }
    private CourseDTO courseConvertToDto(Course obj) {
        return courseMapper.map(obj, CourseDTO.class);
    }

    public EnrollDTO convertToDto(Enroll obj) {
        return mapper.map(obj, EnrollDTO.class);
    }
    public Enroll convertToEntity(EnrollDTO dto){
        return mapper.map(dto, Enroll.class);
    }
}
