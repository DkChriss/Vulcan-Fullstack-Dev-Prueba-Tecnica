import { CommonModule } from '@angular/common';
import { AfterContentInit, ChangeDetectorRef, Component, Inject, ViewEncapsulation } from '@angular/core';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatOptionModule } from '@angular/material/core';
import { MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggle } from '@angular/material/slide-toggle';
import { CourseUpdate } from '../../models/course';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-course-edit-form',
  standalone: true,
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
        MatDialogClose,
        MatSlideToggle
  ],
  templateUrl: './course-edit-form.component.html',
  styleUrl: './course-edit-form.component.scss'
})
export class CourseEditFormComponent implements AfterContentInit{
    constructor(
        @Inject(MAT_DIALOG_DATA) public data,
        private _matDialog: MatDialog,
        private _courseService: CourseService,
        private _cdr: ChangeDetectorRef
    ) {}

    ngAfterContentInit(): void {
        this._cdr.detectChanges();
    }

    form = {
        submitted: false,
        submitting: false,
        formGroup: new FormGroup({
            name: new FormControl<string>(this.data.name,{nonNullable: true, validators: [Validators.required]}),
            status: new FormControl<boolean>(this.data.status,{nonNullable: true, validators: [Validators.required]}),
            places: new FormControl<number>(this.data.places,{nonNullable: true, validators: [Validators.required]}),
            occupiedPlaces: new FormControl<number>(this.data.occupiedPlaces)
        })
    }

    get Form() {return this.form.formGroup.controls}

    formSubmit() {
        this.form.submitted = true;
        if(this.form.formGroup.valid) {
            this.form.submitting = true;
            let updatedCourse: CourseUpdate = this.form.formGroup.getRawValue();
            this._courseService.update(updatedCourse, this.data.id).subscribe({
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
