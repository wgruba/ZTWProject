import { Component } from '@angular/core';
import { ConnectionLookupComponent } from '../connection-lookup/connection-lookup.component';
import { ConenctionWithCityId, Connection} from '../models/connection';
import { PlaceInfo } from '../models/placeInfo';
import { SeatSelectionComponent } from '../seat-selection/seat-selection.component';

@Component({
  selector: 'app-page-connection',
  templateUrl: './page-connection.component.html',
  styleUrls: ['./page-connection.component.css']
})
export class PageConnectionComponent {
  connections: ConenctionWithCityId = new ConenctionWithCityId([], "", "");
  showCourseSelection = false;
  showSeatSelection = false;
  seats: PlaceInfo[] = [];

  showResult(connections: any) {
    this.connections = connections;
    this.showCourseSelection = true;
  }

  showSeat(seats: any) {
    this.seats = seats;
    this.showSeatSelection = true;
  }
}

