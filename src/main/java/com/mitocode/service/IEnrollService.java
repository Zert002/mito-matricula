package com.mitocode.service;

import com.mitocode.dto.CourseStudentDTO;
import com.mitocode.dto.EnrollDTO;
import com.mitocode.model.Enroll;

import java.util.List;

public interface IEnrollService extends ICRUD<Enroll, Integer>{
    List<CourseStudentDTO> listStudentsByCourse();
    EnrollDTO convertToDto(Enroll obj);
    Enroll convertToEntity(EnrollDTO dto);
}
