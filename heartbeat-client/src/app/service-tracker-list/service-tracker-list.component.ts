import {Component, OnInit} from '@angular/core';
import {ServiceTracker} from '../model/service-tracker';
import {ServiceTrackerService} from '../service/service-tracker.service';

@Component({
  selector: 'app-service-tracker-list',
  templateUrl: './service-tracker-list.component.html',
  styleUrls: ['./service-tracker-list.component.css']
})
export class ServiceTrackerListComponent implements OnInit {


  serviceTrackers: ServiceTracker[];

  constructor(private serviceTrackerService: ServiceTrackerService) {
  }

  ngOnInit() {
    this.serviceTrackerService.findAll().subscribe(data => {
      this.serviceTrackers = data;
    });
  }

  getStatusColor(status: string) {
    switch (status) {
      case 'Ok' :
        return 'green';
      case 'Fail' :
        return 'red';
    }
    return 'black';
  }

  reload() {
    window.location.reload();
  }
}
