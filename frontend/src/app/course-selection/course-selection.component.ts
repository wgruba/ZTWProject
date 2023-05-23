import { Component, Input } from '@angular/core';
import { ConenctionWithCityId, Connection } from '../models/connection';
import { HttpClient } from '@angular/common/http';
import { CheckAvailability } from '../models/request-models/checkAvailability';

@Component({
  selector: 'app-course-selection',
  templateUrl: './course-selection.component.html',
  styleUrls: ['./course-selection.component.css']
})
export class CourseSelectionComponent {
  @Input() connections: ConenctionWithCityId = new ConenctionWithCityId([], "", "");

  constructor(private http: HttpClient) {

  }

  checkAvailability(connectionId: string) {
    this.http.post<CheckAvailability>('http://localhost:8080/search/availability', 
      new CheckAvailability(connectionId, this.connections.cityFrom, this.connections.cityTo)).subscribe(
      response => {
        // Handle the response from the backend if needed
        console.log('Request successful:', response);
        //this.availableConnetions.emit(new ConenctionWithCityId(response, this.selectedCityFrom.id, this.selectedCityTo.id));
      },
      error => {
        // Handle any errors that occurred during the request
        console.error('An error occurred:', error);
      }
    );
  }
}
