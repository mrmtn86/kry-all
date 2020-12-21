import {Component, OnInit} from '@angular/core';
import {UserService} from './service/user-service.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent   {


  title = 'heartbeat-client';
  email: string;

  private userService: UserService;


  constructor(userService: UserService, private router: Router) {
    this.email = 'nelly@kry.com';
    this.userService = userService;

  }


  gotoTrackerList() {
    this.router.navigate(['/service-trackers']);
  }

  getUser() {
    return this.userService.getCurrentUser();
  }

  login() {
    this.userService.login(this.email).subscribe(data => {
      if (data != null) {
        localStorage.setItem('user', JSON.stringify(data));
        this.gotoTrackerList();
      }
    });
  }

  loguot() {
    localStorage.removeItem('user');
    this.gotoWellcomeScreen();
  }

  private gotoWellcomeScreen() {
    this.router.navigate(['/']);
  }
}
