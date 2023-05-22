import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { City } from '../models/city';
import { SearchCourse } from '../models/request-models/searchCourse';

@Component({
  selector: 'app-connection-lookup',
  templateUrl: './connection-lookup.component.html',
  styleUrls: ['./connection-lookup.component.css']
})
export class ConnectionLookupComponent {
  selectedCityFrom: City = new City("1", "name");
  selectedCityTo: City = new City("2", "n");
  selectedDepartureDate: Date = new Date;
  cities: City[] = [];

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
  
    this.http.post('http://localhost:8080/search/course', searchCourse).subscribe(
      response => {
        // Handle the response from the backend if needed
        console.log('Request successful:', response);
      },
      error => {
        // Handle any errors that occurred during the request
        console.error('An error occurred:', error);
      }
    );
  }
  
  // private formatDateWithTime(date: Date): string {
  //   const year = date.getFullYear();
  //   const month = this.padNumber(date.getMonth() + 1);
  //   const day = this.padNumber(date.getDate());
  //   const hours = this.padNumber(date.getHours());
  //   const minutes = this.padNumber(date.getMinutes());
  
  //   return `${year}-${month}-${day} ${hours}:${minutes}`;
  // }
  
  // private padNumber(num: number): string {
  //   return num.toString().padStart(2, '0');
  // }
  
  
  compareFn(city1: City, city2: City): boolean {
    return city1 && city2 ? city1.id === city2.id : city1 === city2;
  }  

  

}
