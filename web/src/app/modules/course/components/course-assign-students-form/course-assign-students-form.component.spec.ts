import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseAssignStudentsFormComponent } from './course-assign-students-form.component';

describe('CourseAssignStudentsFormComponent', () => {
  let component: CourseAssignStudentsFormComponent;
  let fixture: ComponentFixture<CourseAssignStudentsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseAssignStudentsFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CourseAssignStudentsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
