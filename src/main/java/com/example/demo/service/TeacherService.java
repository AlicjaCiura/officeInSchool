package com.example.demo.service;

import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    
    public Teacher saveOrUpdate(Teacher personDto) {
        return teacherRepository.save(Teacher.builder()
                                              .firstName(personDto.getFirstName())
                                              .lastName(personDto.getLastName())
                                              .email(personDto.getEmail())
                                              .phone(personDto.getPhone())
                                              .build());
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
