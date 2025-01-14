import { CommonModule } from '@angular/common';
import { AfterContentInit, AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, inject, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatOptionModule } from '@angular/material/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogModule, MatDialogTitle } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { StudentService } from '../../services/student.service';
@Component({
  selector: 'app-student-assign-course-form',
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None,
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
            MatDialogClose
  ],
  templateUrl: './student-assign-course-form.component.html',
  styleUrl: './student-assign-course-form.component.scss'
})
export class StudentAssignCourseFormComponent implements AfterViewInit{

    courses;
  constructor(
        @Inject(MAT_DIALOG_DATA) public data,
        private _matDialog: MatDialog,
        private _studentService: StudentService,
    ) {}

    ngAfterViewInit(): void {
        this.getCourses();
    }

    getCourses() {
        this._studentService.getCourses().subscribe({
            next: (resp: any) => {
                this.courses = resp.data;
            },
            error: (resp) => {
                console.log(resp)
            }
        });
    }

    form = {
        submitted: false,
        submitting: false,
        formGroup: new FormGroup({
            courses: new FormControl<Array<number>>([],{nonNullable: true, validators: [Validators.required]})
        })
    }

    get Form() {return this.form.formGroup.controls}

    formSubmit() {
        this.form.submitted = true;
        if(this.form.formGroup.valid) {
            this.form.submitting = true;
            let courses: any = this.form.formGroup.getRawValue();
            this._studentService.assignCourses(this.data.id,courses).subscribe({
                next: (resp: any) => {
                    console.log(resp);
                },
                error:(error) => {
                    console.log(error);
                    this.form.submitting = false;
                },
                complete:() =>{
                    this.form.formGroup.reset();
                    this.form.submitting = false;
                    this._matDialog.closeAll();
                },
            })
        }
    }
}
