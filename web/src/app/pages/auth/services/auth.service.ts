import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credentials } from '../models/loginRequest';
import { environment } from '../../../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private _httpClient: HttpClient
  ) { }

  login(Credentials: Credentials) {
    return this._httpClient.post<Credentials>(
      `${environment.api}/auth/login`,
      Credentials
    );
  }
}

