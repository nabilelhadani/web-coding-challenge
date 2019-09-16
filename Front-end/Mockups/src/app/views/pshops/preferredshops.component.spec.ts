import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreferredshopsComponent } from './preferredshops.component';

describe('PreferredshopsComponent', () => {
  let component: PreferredshopsComponent;
  let fixture: ComponentFixture<PreferredshopsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreferredshopsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreferredshopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
