import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../model/user';

@Injectable()
export class UserService {

  private readonly usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/v1/user';
  }


  login(email: string) {
    const params = new HttpParams().set('email', email);
    return this.http.get<User>(this.usersUrl, {params});
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }
}
