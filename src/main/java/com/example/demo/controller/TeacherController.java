package com.example.demo.controller;


import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> getTeachers(){
        return teacherService.findAll();
    }


}
