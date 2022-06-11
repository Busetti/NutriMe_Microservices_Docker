import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainMealComponent } from './main-meal.component';

describe('MainMealComponent', () => {
  let component: MainMealComponent;
  let fixture: ComponentFixture<MainMealComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainMealComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainMealComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
