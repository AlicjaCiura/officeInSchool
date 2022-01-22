import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeacherComponent } from './teacher/teacher.component';
import { TeacherDetailsComponent } from './teacher-details/teacher-details.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
{ path: 'dashboard', component: DashboardComponent },
{ path: 'teachers', component: TeacherComponent }   ,
{ path: 'detail/:id', component: TeacherDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
