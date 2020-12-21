import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ServiceTrackerListComponent} from './service-tracker-list/service-tracker-list.component';
import {TrackerFormComponent} from './tracker-form/tracker-form.component';

const routes: Routes = [
  {path: 'service-trackers', component: ServiceTrackerListComponent},
  {path: 'tracker-form', component: TrackerFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
