import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(
    private _httpClient: HttpClient
  ) { }

  home() {
    return this._httpClient.get(
        `${environment.api}/students/home`
    );
  }

  generalCourses() {
    return this._httpClient.get(
        `${environment.api}/courses/general`
    );
  }
}
