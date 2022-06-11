import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainFavoritesComponent } from './main-favorites.component';

describe('MainFavoritesComponent', () => {
  let component: MainFavoritesComponent;
  let fixture: ComponentFixture<MainFavoritesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainFavoritesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainFavoritesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
