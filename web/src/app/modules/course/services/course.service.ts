import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'app/environments/environment';
import { Observable } from 'rxjs';
import { Course, CourseStore, CourseUpdate } from '../models/course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(
    private _httpClient: HttpClient
  ) { }

    list(page: number, size: number): Observable<any> {
        const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString());

        return this._httpClient.get(`${environment.api}/courses`, {params});
    }

    store(course: CourseStore) {
        return this._httpClient.post<CourseStore>(
            `${environment.api}/courses`,
            course
        );
    }

    show(id: number) {
        return this._httpClient.get<Course>(
            `${environment.api}/courses/${id}`
        );
    }

    update(course: CourseUpdate, id: number) {
        return this._httpClient.put<CourseUpdate>(
            `${environment.api}/courses/${id}`,
            course
        );
    }

    destroy(id: number) {
        return this._httpClient.delete<number>(
            `${environment.api}/courses/${id}`
        );
    }

    assignStudents(id: number, students: Array<number>){
        return this._httpClient.post(
            `${environment.api}/courses/${id}/assign-students`,
            students
        );
    }

    getStudents() {
        return this._httpClient.get(
            `${environment.api}/students/get-students`
        );
    }
}
