import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import {Student} from "./student"
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(public http: HttpClient) { }

  public findAll(){
    return this.http.get<Student[]>("/api/v1/students/all");
  }

  public findById(id: number) {
    return this.http.get<Student>(`api/v1/students/${id}/id`);
  }

  public delete(id: number){
    console.log('send delete method');
    return this.http.delete(`api/v1/students/${id}/id`);
    console.log('send delete method');
  }

  public save(student: Student){

    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };
    console.log('id - ' + student.id);
    if(student.id == -1){
    console.log('send post method');
      return this.http.post("api/v1/students/", {firstName: student.firstName, lastName: student.lastName});
    } else {
    console.log('send put method');
      return this.http.put("/api/v1/students/", {id: student.id, firstName: student.firstName, lastName: student.lastName});
    }
  }

}

