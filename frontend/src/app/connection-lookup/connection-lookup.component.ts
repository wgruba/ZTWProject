import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { City } from '../models/city';
import { SearchCourse } from '../models/request-models/searchCourse';
import { ConenctionWithCityId, Connection } from '../models/connection';

@Component({
  selector: 'app-connection-lookup',
  templateUrl: './connection-lookup.component.html',
  styleUrls: ['./connection-lookup.component.css']
})
export class ConnectionLookupComponent {
  selectedCityFrom: City = new City("", "");
  selectedCityTo: City = new City("", "");
  selectedDepartureDate: Date = new Date;
  cities: City[] = [];
  @Output() availableConnetions = new EventEmitter<ConenctionWithCityId>;

  constructor(private http: HttpClient) {
    this.retrieveCityList();
  }

  retrieveCityList() {
      this.http.get<City[]>('http://localhost:8080/search/city').subscribe(cities => {
        this.cities = cities;
      })
  }

  submitForm() {
    //const formattedDepartureTime = this.formatDateWithTime(this.selectedDepartureDate);
  
    const searchCourse: SearchCourse = {
      startCity: this.selectedCityFrom.id,
      endCity: this.selectedCityTo.id,
      departureTime: this.selectedDepartureDate
    };
  
    this.http.post<Connection[]>('http://localhost:8080/search/course', searchCourse).subscribe(
      response => {
        // Handle the response from the backend if needed
        console.log('Request successful:', response);
        this.availableConnetions.emit(new ConenctionWithCityId(response, this.selectedCityFrom.id, this.selectedCityTo.id));
      },
      error => {
        // Handle any errors that occurred during the request
        console.error('An error occurred:', error);
      }
    );
  }
  
  
  compareFn(city1: City, city2: City): boolean {
    return city1 && city2 ? city1.id === city2.id : city1 === city2;
  }  

  

}
