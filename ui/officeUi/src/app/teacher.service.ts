import { Injectable } from '@angular/core';
import { Teacher } from './teacher';
import { TEACHERS } from './mock-teacher';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

private teachersUrl = '/api/teachers';

  constructor( private http: HttpClient) { }

  getAllTeachers(): Observable<Teacher[]> {
    return this.http.get<Teacher[]>(this.teachersUrl);
  }

  getTeacher(id: number): Observable<Teacher> {
    const teacher = TEACHERS.find(h => h.id === id)!;
    return of(teacher);
  }
}
