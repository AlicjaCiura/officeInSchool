package com.example.demo.service;

import com.example.demo.dto.PersonDto;
import com.example.demo.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    public Teacher saveOrUpdate(PersonDto personDto) {
        return teacherRepository.save(Teacher.builder()
                                              .firstName(personDto.getName())
                                              .lastName(personDto.getSurname())
                                              .build());
    }
}
