import { Component, OnInit } from '@angular/core';
import {StudentService} from "../service/student.service";
import {Student} from "../service/student"
import {Router} from "@angular/router";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.less']
})
export class StudentListComponent implements OnInit {

  students: Student[] = [];

  constructor(
    public studentService: StudentService,
    public router: Router) { }

  ngOnInit(): void {
    this.retriveAllStudents();
  }

  private retriveAllStudents(){
    this.studentService.findAll().subscribe(
      students => {
        console.log('New data from server!');
        this.students = students;
      }, error => {
        console.log('Error $(error)');
      }
    );
  }

  public delete(id: number){
    this.studentService.delete(id).subscribe(
    data=>{this.retriveAllStudents();},
    error=>{console.log('Error ${error}')});
  }

}
