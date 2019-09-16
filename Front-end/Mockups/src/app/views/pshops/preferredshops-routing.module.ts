import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PreferredshopsComponent } from './preferredshops.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Main'
    },
      children: [
        {
          path: '',
          redirectTo: 'preferredshops'
        },
        {
          path: '',
          component: PreferredshopsComponent,
          data: {
            title: 'preferredshops'
          }
        },
      ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreferredshopsRoutingModule { }
