import { TestBed } from '@angular/core/testing';

import { ServiceTrackerService } from './service-tracker.service';

describe('ServiceTrackerService', () => {
  let service: ServiceTrackerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceTrackerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
