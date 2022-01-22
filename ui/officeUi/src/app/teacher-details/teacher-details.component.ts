import { Component, OnInit, Input } from '@angular/core'
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {Teacher} from '../teacher'
import {TeacherService} from   '../teacher.service'

@Component({
  selector: 'app-teacher-details',
  templateUrl: './teacher-details.component.html',
  styleUrls: ['./teacher-details.component.css']
})
export class TeacherDetailsComponent implements OnInit {

@Input() teacher?: Teacher;

  constructor( private route: ActivatedRoute,
                  private service: TeacherService,
                  private location: Location) { }

ngOnInit(): void {
    this.getTeacher();
  }

  getTeacher(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.getTeacher(id)
      .subscribe(teacher => this.teacher = teacher);
  }

  goBack(): void {
    this.location.back();
  }

}
