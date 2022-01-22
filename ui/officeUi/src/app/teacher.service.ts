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
    // For now, assume that a hero with the specified `id` always exists.
    // Error handling will be added in the next step of the tutorial.
    const teacher = TEACHERS.find(h => h.id === id)!;
    return of(teacher);
  }
}
