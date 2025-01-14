import { CommonModule } from '@angular/common';
import { AfterViewInit, ChangeDetectionStrategy, Component, Inject, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatOptionModule } from '@angular/material/core';
import { MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-course-assign-students-form',
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
  templateUrl: './course-assign-students-form.component.html',
  styleUrl: './course-assign-students-form.component.scss'
})
export class CourseAssignStudentsFormComponent implements AfterViewInit{

    students;
    constructor(
        @Inject(MAT_DIALOG_DATA) public data,
        private _matDialog: MatDialog,
        private _courseService: CourseService,
    ) {}

    ngAfterViewInit(): void {
        this.getStudents();
    }

    getStudents() {
        this._courseService.getStudents().subscribe({
            next: (resp: any) => {
                this.students = resp.data;
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
                students: new FormControl<Array<number>>([],{nonNullable: true, validators: [Validators.required]})
            })
        }

        get Form() {return this.form.formGroup.controls}

        formSubmit() {
            this.form.submitted = true;
            if(this.form.formGroup.valid) {
                this.form.submitting = true;
                let students: any = this.form.formGroup.getRawValue();
                this._courseService.assignStudents(this.data.id,students).subscribe({
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
