import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NearbyshopsRoutingModule } from './nearbyshops-routing.module';
import { NearbyshopsComponent } from './nearbyshops.component';

@NgModule({
  declarations: [
    NearbyshopsComponent
  ],
  imports: [
    CommonModule,
    NearbyshopsRoutingModule
  ]
})
export class NearbyshopsModule { }
