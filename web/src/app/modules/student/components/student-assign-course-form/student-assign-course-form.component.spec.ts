import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentAssignCourseFormComponent } from './student-assign-course-form.component';

describe('StudentAssignCourseFormComponent', () => {
  let component: StudentAssignCourseFormComponent;
  let fixture: ComponentFixture<StudentAssignCourseFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentAssignCourseFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StudentAssignCourseFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
