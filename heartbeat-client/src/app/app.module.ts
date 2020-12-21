import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {UserService} from './service/user-service.service';
import {ServiceTrackerListComponent} from './service-tracker-list/service-tracker-list.component';
import {TrackerFormComponent} from './tracker-form/tracker-form.component';
import {ErrorInterceptor} from './helper/error.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    ServiceTrackerListComponent,
    TrackerFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [UserService,
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
