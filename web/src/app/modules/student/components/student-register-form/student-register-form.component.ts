import { CommonModule } from '@angular/common';
import { Component, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatOptionModule } from '@angular/material/core';
import { MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogModule, MatDialogTitle } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { StudentService } from '../../services/student.service';
import { StudentStore } from '../../models/student';
import { FuseAlertService } from '@fuse/components/alert';

@Component({
  selector: 'app-student-register-form',
  encapsulation: ViewEncapsulation.None,
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatDividerModule,
    MatCheckboxModule,
    MatRadioModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
  ],
  templateUrl: './student-register-form.component.html',
  styleUrl: './student-register-form.component.scss'
})
export class StudentRegisterFormComponent {

    constructor(
        private _matDialog: MatDialog,
        private _studentService: StudentService,
        private _alert: FuseAlertService
    ) {}

    form = {
        submitted: false,
        submitting: false,
        formGroup: new FormGroup({
            firstName: new FormControl<string>(null,{nonNullable: true, validators: [Validators.required]}),
            lastName: new FormControl<string>(null,{nonNullable: true, validators: [Validators.required]}),
            gender: new FormControl<string>(null,{nonNullable: true, validators: [Validators.required]}),
            age: new FormControl<number>(null,{nonNullable: true, validators: [Validators.required]})
        })
    }

    get Form() {return this.form.formGroup.controls}

    formSubmit() {
        this.form.submitted = true;
        if(this.form.formGroup.valid) {
            this.form.submitting = true;
            let newStudent: StudentStore = this.form.formGroup.getRawValue();
            this._studentService.store(newStudent).subscribe({
                next: (resp: any) => {
                    this._alert.show('Se ha registrado correctamente al alumno');
                },
                error:(error) => {
                    console.log(error);
                    this.form.submitting = false;
                },
                complete:() =>{
                    this._matDialog.closeAll();
                    this.form.formGroup.reset()
                    this.form.submitting = false
                },
            })
        }
    }

}
