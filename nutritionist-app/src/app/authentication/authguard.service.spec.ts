import { TestBed } from '@angular/core/testing';

import { AuthguardService } from './authguard.service';

describe('AuthguardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthguardService = TestBed.get(AuthguardService);
    expect(service).toBeTruthy();
  });
});
