import { CommonModule } from '@angular/common';
import { Component, ViewEncapsulation } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { BehaviorSubject, combineLatest, debounceTime, distinctUntilChanged, switchMap, tap } from 'rxjs';
import { CourseService } from '../../services/course.service';
import { FuseConfirmationService } from '@fuse/services/confirmation';
import { CourseRegisterFormComponent } from '../../components/course-register-form/course-register-form.component';
import { CourseEditFormComponent } from '../../components/course-edit-form/course-edit-form.component';
import { CourseAssignStudentsFormComponent } from '../../components/course-assign-students-form/course-assign-students-form.component';

@Component({
  selector: 'app-course-page',
  standalone: true,
  encapsulation: ViewEncapsulation.None,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule
  ],
  templateUrl: './course-page.component.html',
  styleUrl: './course-page.component.scss'
})
export class CoursePageComponent {

    totalElements = 0;
    currentPage$ = new BehaviorSubject<number>(0);
    pageSize$ = new BehaviorSubject<number>(10);

    courseTable = {
        reload: new BehaviorSubject<void>(null)
    }

    courseList$ = combineLatest([
        this.courseTable.reload,
        this.currentPage$,
        this.pageSize$
    ]).pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap(() => this._courseService.list(
            this.currentPage$.getValue(),
            this.pageSize$.getValue()
        ).pipe(
            tap((res:any) => {
                this.totalElements = res["data"]["totalElements"];
            })
        ))
    );

    onPageChange($event) {
        this.pageSize$.next($event.pageSize);
        this.currentPage$.next($event.pageIndex);
    }

    constructor(
        private _courseService: CourseService,
        private _matDialog: MatDialog,
        private _fuseConfirmationDialog: FuseConfirmationService
    ){}

    openCourseFormRegister() {
        this._matDialog.open(CourseRegisterFormComponent, {
            width: '500px',
        })
        .afterClosed()
        .subscribe(() => {
            this.courseTable.reload.next();
        });
    }

    openCourseEditForm(course: any) {
        this._matDialog.open(
            CourseEditFormComponent, {
                data: course,
                width: '500px'
            }
        ).afterClosed()
        .subscribe(()=> {
            this.courseTable.reload.next();
        })
    }

    openCourseDeleteConfirm(course: any) {
        const dialogRef = this._fuseConfirmationDialog.open({
            "title": "Eliminar curso",
            "message": "Estas seguro de eliminar este curso? <span class=\"font-medium\">Esta accion no puede ser revertida</span>",
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
                this._courseService.destroy(course.id).subscribe({
                    next: (resp) => {
                        this.courseTable.reload.next()
                    },
                    error: (error) => {
                        console.log(error)
                    }
                })
            }
        });
    }

    openAssignStudents(course: any){
        if(!course.status) {
            this._matDialog.open(
                CourseAssignStudentsFormComponent, {
                    data: course,
                    width: '500px'
                }
            ).afterClosed()
            .subscribe(() => {
                this.courseTable.reload.next();
            });
        }
    }

    groupStudents(students: string[]): string[][] {
        const groups: string[][] = [];
        for (let i = 0; i < students.length; i += 2) {
            groups.push(students.slice(i, i + 2));
        }
        return groups;
    }


}
