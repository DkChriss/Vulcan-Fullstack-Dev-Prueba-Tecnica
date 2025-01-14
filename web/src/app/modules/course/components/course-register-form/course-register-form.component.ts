import { CommonModule } from '@angular/common';
import { Component, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatOptionModule } from '@angular/material/core';
import { MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, MatDialog } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { CourseService } from '../../services/course.service';
import { CourseStore } from '../../models/course';
import { MatSlideToggle } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-course-register-form',
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
    MatSlideToggle
    ],
  templateUrl: './course-register-form.component.html',
  styleUrl: './course-register-form.component.scss'
})
export class CourseRegisterFormComponent {
    constructor(
        private _matDialog: MatDialog,
        private _courseService: CourseService
    ) {}

    form = {
        submitted: false,
        submitting: false,
        formGroup: new FormGroup({
            name: new FormControl<string>('',{nonNullable: true, validators: [Validators.required]}),
            status: new FormControl<boolean>(true,{nonNullable: true, validators: [Validators.required]}),
            places: new FormControl<number>(0,{nonNullable: true, validators: [Validators.required]}),
        })
    }

    get Form() {return this.form.formGroup.controls}

    formSubmit() {
        this.form.submitted = true;
        if(this.form.formGroup.valid) {
            this.form.submitting = true;
            let newCourse: CourseStore = this.form.formGroup.getRawValue();
            this._courseService.store(newCourse).subscribe({
                next: (resp: any) => {
                    console.log(resp)
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
