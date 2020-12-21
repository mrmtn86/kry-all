import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceTrackerListComponent } from './service-tracker-list.component';

describe('ServiceTrackerListComponent', () => {
  let component: ServiceTrackerListComponent;
  let fixture: ComponentFixture<ServiceTrackerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceTrackerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceTrackerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
