import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'app/environments/environment';
import { StudentStore, Student, StudentUpdate } from '../models/student';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

    constructor(
        private _httpClient: HttpClient
      ) { }

      list(page: number, size: number): Observable<any> {
        const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString());

        return this._httpClient.get(`${environment.api}/students`, {params});
      }

      store(student: StudentStore) {
        return this._httpClient.post<StudentStore>(
          `${environment.api}/students`,
          student
        );
      }

      show(id: number) {
        return this._httpClient.get<Student>(
          `${environment.api}/students/${id}`
        );
      }

      update(student: StudentUpdate, id: number) {
        return this._httpClient.put<StudentUpdate>(
          `${environment.api}/students/${id}`,
          student
        );
      }

      destroy(id: number) {
        return this._httpClient.delete<number>(
          `${environment.api}/students/${id}`
        );
      }

      assignCourses(id:number, courses: Array<number>) {
        return this._httpClient.post(
            `${environment.api}/students/${id}/assign-courses`,
            courses
        );
      }

      getCourses() {
        return this._httpClient.get(
            `${environment.api}/courses/get-courses`
        );
      }
}
