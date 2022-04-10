import { Component, OnInit } from '@angular/core';
import {Teacher} from '../teacher';
import {TeacherService} from   '../teacher.service'
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import UsersJson from '../users.json';

@Component({
  selector: 'teacher',
  templateUrl: 'teacher.component.html',
  styleUrls: ['teacher.component.css']
})
export class TeacherComponent implements OnInit {

public displayedColumns = ['firstName', 'lastName', 'studentEmail', 'yearOfStudy', 'registrationNumber', 'course' ];

teachers: Teacher[] = UsersJson    ;
selected?: Teacher       ;

constructor(private service: TeacherService) {
}

  ngOnInit(): void {
  console.log("Test")
   this.getTeachers()
  }

  getTeachers(): void {
    this.service.getAllTeachers()
    .subscribe(teachersList => this.teachers = teachersList);
  }

    onSelect(teacher: Teacher): void {
      this.selected = teacher;
    }

}
