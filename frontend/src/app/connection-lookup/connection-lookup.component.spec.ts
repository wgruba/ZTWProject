import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectionLookupComponent } from './connection-lookup.component';

describe('ConnectionLookupComponent', () => {
  let component: ConnectionLookupComponent;
  let fixture: ComponentFixture<ConnectionLookupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConnectionLookupComponent]
    });
    fixture = TestBed.createComponent(ConnectionLookupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
