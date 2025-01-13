import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Credentials } from '../../models/loginRequest';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login-page',
  standalone: false,

  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

  constructor(
    private _authService: AuthService
  ) {}

  form = {
    submitted: false,
    submitting: false,
    formGroup: new FormGroup({
      username: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
      password: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
    })
  }

  get Form() {return this.form.formGroup.controls}

  formSubmit() {
    this.form.submitted = true;
    if(this.form.formGroup.valid) {
      this.form.submitting = true;
      let user: Credentials = this.form.formGroup.getRawValue();
      this._authService.login(user).subscribe({
        next: (resp: any) => {
          if(resp.token) {
            localStorage.setItem('token', resp.token);
          }
        },
        error: (error) => {
          console.log(error);
        },
        complete: () => {
          this.form.submitting = false;
        }
      });
    }
  }
}
