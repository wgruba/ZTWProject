import { Component, Input } from '@angular/core';
import { PlaceInfo } from '../models/placeInfo';
import { CheckAvailability } from '../models/request-models/checkAvailability';

@Component({
  selector: 'app-seat-selection',
  templateUrl: './seat-selection.component.html',
  styleUrls: ['./seat-selection.component.css']
})
export class SeatSelectionComponent {
  @Input() seats: PlaceInfo[] = [];


  selectSeat(seatId: string): void {
    const seat = this.seats.find(s => s.placeId === seatId);
    if (seat != null && seat.available) {
      seat.isSelected = !seat.isSelected;
      console.log('Selected seat:', seat.placeId);
    }
  }
  
  buyTicket() {
    const selectedSeats = this.getSelectedSeats();
    console.log("Request will be sent. N. selected: " + selectedSeats.length);
  }

  getSelectedSeats(): string[] {
    return this.seats.filter(place => place.isSelected).map(place => place.placeId);
  }

}
