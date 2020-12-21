import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ServiceTracker} from '../model/service-tracker';
import {ServiceTrackerService} from '../service/service-tracker.service';
import {UserService} from '../service/user-service.service';

@Component({
  selector: 'app-tracker-form',
  templateUrl: './tracker-form.component.html',
  styleUrls: ['./tracker-form.component.css']
})
export class TrackerFormComponent implements OnInit {
  serviceTracker: ServiceTracker;

  id: number;
  titleName: string;
  alertStatus: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private trackerService: ServiceTrackerService,
    private userService: UserService) {
    this.serviceTracker = new ServiceTracker();

  }

  onSubmit() {
    this.trackerService.save(this.serviceTracker).subscribe(result => this.gotoTrackerList());
  }

  delete() {
    if (confirm('Are you sure to delete ' + this.titleName)) {
      this.trackerService.delete(this.serviceTracker.id).subscribe(result => this.gotoTrackerList());
    }
  }

  reload() {
    window.location.reload();
    this.initTracker(this.serviceTracker.id);
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.initTracker(params.id);
    });
  }

  private initTracker(id: number) {
    if (id != null) {
      this.trackerService.findById(id).subscribe(data => {
        this.serviceTracker = data;
        this.titleName = this.serviceTracker.name;
        this.updateAlertStatus();
      });
    } else {
      this.serviceTracker = new ServiceTracker();
      this.serviceTracker.userId = this.userService.getCurrentUser().id;
      this.titleName = 'Add New';
      this.updateAlertStatus();
    }

  }

  gotoTrackerList() {
    this.router.navigate(['/service-trackers']);
  }

  updateAlertStatus() {
    switch (this.serviceTracker.status) {
      case 'Ok' :
        return 'success';
      case 'Fail' :
        return 'danger';
      case 'Unknown' :
        return 'dark';

    }
    return 'dark';
  }


}
