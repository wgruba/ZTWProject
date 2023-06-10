import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ConenctionWithCityId, Connection } from '../models/connection';
import { HttpClient } from '@angular/common/http';
import { CheckAvailability } from '../models/request-models/checkAvailability';
import { PlaceInfo, PlaceInfoWithCourseStops } from '../models/placeInfo';
import { DataService } from '../data.service';


@Component({
  selector: 'app-searched-content',
  templateUrl: './searched-content.component.html',
  styleUrls: ['./searched-content.component.css']
})
export class SearchedContentComponent {
  @Input() connections: ConenctionWithCityId = new ConenctionWithCityId([], "", "");
  @Output() seats = new EventEmitter<PlaceInfoWithCourseStops>

  constructor(private http: HttpClient, private dataService: DataService) {
    this.dataService.currentConnection.subscribe(connection => this.connections = connection);
  }

  checkAvailability(connectionId: string) {
    this.http.post<PlaceInfo[]>('http://localhost:8080/search/availability', 
      new CheckAvailability(connectionId, this.connections.cityFrom, this.connections.cityTo)).subscribe(
      response => {
        console.log('Request successful:', response);
        this.seats.emit(new PlaceInfoWithCourseStops(response, connectionId, this.connections.cityFrom, this.connections.cityTo));
      },
      error => {
        // Handle any errors that occurred during the request
        console.error('An error occurred:', error);
      }
    );
  }
}
