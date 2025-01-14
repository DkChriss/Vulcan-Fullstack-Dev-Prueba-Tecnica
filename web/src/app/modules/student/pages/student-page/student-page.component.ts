import { Component, ViewEncapsulation } from '@angular/core';
import { BehaviorSubject, combineLatest, debounceTime, distinctUntilChanged, switchMap, tap } from 'rxjs';
import { StudentRegisterFormComponent } from '../../components/student-register-form/student-register-form.component';
import { StudentService } from '../../services/student.service';
import { MatDialog } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { StudentEditFormComponent } from '../../components/student-edit-form/student-edit-form.component';
import { FuseConfirmationService } from '@fuse/services/confirmation';
import { MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import { StudentAssignCourseFormComponent } from '../../components/student-assign-course-form/student-assign-course-form.component';

@Component({
  selector: 'app-student-page',
  standalone: true,
  encapsulation: ViewEncapsulation.None,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule],
  templateUrl: './student-page.component.html',
  styleUrl: './student-page.component.scss'
})
export class StudentPageComponent {

    totalElements = 0;
    currentPage$ = new BehaviorSubject<number>(0);
    pageSize$ = new BehaviorSubject<number>(10);

    studentTable = {
        reload: new BehaviorSubject<void>(null)
      }

      studentList$ = combineLatest([
        this.studentTable.reload,
        this.currentPage$,
        this.pageSize$
      ])
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap(()=> this._studentService.list(
            this.currentPage$.getValue(),
            this.pageSize$.getValue()
        )
        .pipe(
          tap((res:any) => {
            this.totalElements = res["data"]["totalElements"];
        })
        ))
      )

      onPageChange($event) {
        this.pageSize$.next($event.pageSize);
        this.currentPage$.next($event.pageIndex);
      }

      constructor(
        private _studentService: StudentService,
        private _matDialog: MatDialog,
        private _fuseConfirmationDialog: FuseConfirmationService
      ){}

      openStudentFormRegister() {
        this._matDialog
            .open(StudentRegisterFormComponent,{
                width: '500px',
            })
            .afterClosed()
            .subscribe(() => {
               this.studentTable.reload.next()
            });
      }

      openStudentFormEdit(student: any) {
        this._matDialog
            .open(StudentEditFormComponent,{
                data: student,
                width: '500px',
            })
            .afterClosed()
            .subscribe(() => {
               this.studentTable.reload.next()
            });
      }

      openStudentDeleteConfirm(student: any) {
        const dialogRef = this._fuseConfirmationDialog.open({
            "title": "Eliminar estudiante",
            "message": "Estas seguro de eliminar este estudiante? <span class=\"font-medium\">Esta accion no puede ser revertida</span>",
            "icon": {
              "show": true,
              "name": "heroicons_outline:exclamation-triangle",
              "color": "warn"
            },
            "actions": {
              "confirm": {
                "show": true,
                "label": "Eliminar",
                "color": "warn"
              },
              "cancel": {
                "show": true,
                "label": "Cancelar"
              }
            },
            "dismissible": true
        });

        dialogRef.afterClosed().subscribe((result) => {
            if(result === 'confirmed') {
                this._studentService.destroy(student.id).subscribe({
                    next: (resp) => {
                        this.studentTable.reload.next()
                    },
                    error: (error) => {
                        console.log(error)
                    }
                })
            }
        });
      }

      openAssignCourses(student: any) {
        this._matDialog
        .open(StudentAssignCourseFormComponent,{
            data: student,
            width: '500px',
        })
        .afterClosed()
        .subscribe(() => {
           this.studentTable.reload.next()
        });
      }
}

