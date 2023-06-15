import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectionSiteComponent } from './selection-site.component';

describe('SelectionSiteComponent', () => {
  let component: SelectionSiteComponent;
  let fixture: ComponentFixture<SelectionSiteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectionSiteComponent]
    });
    fixture = TestBed.createComponent(SelectionSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
