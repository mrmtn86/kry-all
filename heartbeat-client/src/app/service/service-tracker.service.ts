import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ServiceTracker} from '../model/service-tracker';
import {UserService} from './user-service.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceTrackerService {

  private readonly servicetrackerUrl: string;
  private userService: UserService;

  constructor(private http: HttpClient, userService: UserService) {
    this.userService = userService;
    this.servicetrackerUrl = 'http://localhost:8080/v1/service-tracker';
  }

  public findAll(): Observable<ServiceTracker[]> {

    const user = this.userService.getCurrentUser();

    const params = new HttpParams().set('userId', user.id.toString());

    return this.http.get<ServiceTracker[]>(this.servicetrackerUrl, {params});
  }

  public save(serviceTracker: ServiceTracker) {
    return this.http.post<ServiceTracker>(this.servicetrackerUrl, serviceTracker);
  }

  findById(trackerId: number) {
    const params = new HttpParams().set('trackerId', trackerId.toString());
    return this.http.get<ServiceTracker>(this.servicetrackerUrl, {params});
  }

  delete(trackerId: number) {
    const params = new HttpParams().set('trackerId', trackerId.toString());
    const options = {params};
    return this.http.delete(this.servicetrackerUrl, options);

  }
}
