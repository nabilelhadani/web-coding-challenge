import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NearbyshopsComponent } from './nearbyshops.component';

describe('NearbyshopsComponent', () => {
  let component: NearbyshopsComponent;
  let fixture: ComponentFixture<NearbyshopsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NearbyshopsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NearbyshopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
