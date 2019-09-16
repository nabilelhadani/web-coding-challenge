import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreferredshopsRoutingModule } from './preferredshops-routing.module';
import { PreferredshopsComponent } from './preferredshops.component';

@NgModule({
  declarations: [
    PreferredshopsComponent
  ],
  imports: [
    CommonModule,
    PreferredshopsRoutingModule
  ]
})
export class PreferredshopsModule { }
