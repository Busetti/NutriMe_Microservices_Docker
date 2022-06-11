import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserBmiComponent } from './user-bmi.component';

describe('UserBmiComponent', () => {
  let component: UserBmiComponent;
  let fixture: ComponentFixture<UserBmiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserBmiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserBmiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
