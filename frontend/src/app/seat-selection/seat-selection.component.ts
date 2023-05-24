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
    console.log(seatId);
    const seat = this.seats.find(s => s.placeId === seatId);
    console.log(seat?.available)
    if (seat != null && seat.available) {
      seat.isSelected = !seat.isSelected;
      console.log('Selected seat:', seat.placeId);
    }
  }
  
}
