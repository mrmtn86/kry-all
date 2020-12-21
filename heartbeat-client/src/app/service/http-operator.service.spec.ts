import { TestBed } from '@angular/core/testing';

import { HttpOperatorService } from './http-operator.service';

describe('HttpOperatorService', () => {
  let service: HttpOperatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpOperatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
