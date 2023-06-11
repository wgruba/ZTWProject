import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartSiteComponent } from './cart-site.component';

describe('CartSiteComponent', () => {
  let component: CartSiteComponent;
  let fixture: ComponentFixture<CartSiteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CartSiteComponent]
    });
    fixture = TestBed.createComponent(CartSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
