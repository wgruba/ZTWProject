import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ConenctionWithCityId, Connection } from '../models/connection';
import { HttpClient } from '@angular/common/http';
import { CheckAvailability } from '../models/request-models/checkAvailability';
import { PlaceInfo } from '../models/placeInfo';

@Component({
  selector: 'app-course-selection',
  templateUrl: './course-selection.component.html',
  styleUrls: ['./course-selection.component.css']
})
export class CourseSelectionComponent {
  @Input() connections: ConenctionWithCityId = new ConenctionWithCityId([], "", "");
  @Output() seats = new EventEmitter<PlaceInfo[]>

  constructor(private http: HttpClient) {

  }

  checkAvailability(connectionId: string) {
    this.http.post<PlaceInfo[]>('http://localhost:8080/search/availability', 
      new CheckAvailability(connectionId, this.connections.cityFrom, this.connections.cityTo)).subscribe(
      response => {
        console.log('Request successful:', response);
        this.seats.emit(response);
      },
      error => {
        // Handle any errors that occurred during the request
        console.error('An error occurred:', error);
      }
    );
  }
}
