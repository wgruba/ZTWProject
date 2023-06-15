import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartContentComponent } from './cart-content.component';

describe('CartContentComponent', () => {
  let component: CartContentComponent;
  let fixture: ComponentFixture<CartContentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CartContentComponent]
    });
    fixture = TestBed.createComponent(CartContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
