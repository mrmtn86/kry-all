import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor() {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status === 500) {
        console.log('hatta');
        console.log(err.message);
      }
      if (err.status === 400) {
        confirm(err?.error.errors[0]);
      }

      const error = err.error.message || err.statusText;
      return throwError(error);
    }));
  }
}
