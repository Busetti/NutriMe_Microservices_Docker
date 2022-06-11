import { TestBed } from '@angular/core/testing';

import { MealService } from './meal.service';

describe('MealService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MealService = TestBed.get(MealService);
    expect(service).toBeTruthy();
  });
});
