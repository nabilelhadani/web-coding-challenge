import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NearbyshopsComponent } from './nearbyshops.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Main'
    },
      children: [
        {
          path: '',
          redirectTo: 'nearbyshops'
        },
        {
          path: '',
          component: NearbyshopsComponent,
          data: {
            title: 'nearbyshops'
          }
        },
      ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NearbyshopsRoutingModule { }
