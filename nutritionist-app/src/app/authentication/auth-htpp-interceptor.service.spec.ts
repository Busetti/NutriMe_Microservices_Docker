import { TestBed } from '@angular/core/testing';

import { AuthHtppInterceptorService } from './auth-htpp-interceptor.service';

describe('AuthHtppInterceptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthHtppInterceptorService = TestBed.get(AuthHtppInterceptorService);
    expect(service).toBeTruthy();
  });
});
