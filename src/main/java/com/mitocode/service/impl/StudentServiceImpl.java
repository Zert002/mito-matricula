package com.mitocode.service.impl;

import com.mitocode.model.Student;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IStudentRepo;
import com.mitocode.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparingInt;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {
    private final IStudentRepo repo;
    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }

    /*Other Functions*/
    @Override
    public List<Student> listAllByAgeOrderDesc() {
        return repo.findAll().stream().sorted(comparingInt(Student::getAge).reversed()).toList();
    }
}
