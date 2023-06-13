import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cart-content',
  templateUrl: './cart-content.component.html',
  styleUrls: ['./cart-content.component.css']
})
export class CartContentComponent {

  constructor(private router: Router) { }
  
  confirmPurchase(): void {
    this.router.navigate(['/']);
  }

}
