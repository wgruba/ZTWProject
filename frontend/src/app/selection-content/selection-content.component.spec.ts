import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectionContentComponent } from './selection-content.component';

describe('SelectionContentComponent', () => {
  let component: SelectionContentComponent;
  let fixture: ComponentFixture<SelectionContentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectionContentComponent]
    });
    fixture = TestBed.createComponent(SelectionContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
