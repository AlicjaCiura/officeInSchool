import { Component, OnInit } from '@angular/core';
import {Teacher} from '../teacher';
import {TeacherService} from   '../teacher.service'
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {

teachers: Teacher[] = []

constructor(
  private service: TeacherService
) {}

  ngOnInit(): void {
    this.getTeachers()
  }

  getTeachers(): void {
    this.service.getAllTeachers()
    .subscribe(teachers => this.teachers = teachers);
  }

}
