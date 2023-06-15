import { Component, EventEmitter, Output, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'
import { City } from '../models/city';
import { SearchCourse } from '../models/request-models/searchCourse';
import { ConenctionWithCityId, Connection } from '../models/connection';
import { DataService } from '../data.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../models/user';



@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  selectedCityFrom: City = new City("", "");
  selectedCityTo: City = new City("", "");
  selectedDepartureDate: Date = new Date;
  cities: City[] = [];
  user: User;
  @Output() availableConnetions = new EventEmitter<ConenctionWithCityId>;

  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router: Router, private dataService: DataService) {
    this.retrieveCityList();
  }

  ngOnInit(): void {
    const body = { username: this.user.email }; 
    this.http.post<Object>('http://localhost:8080/userinfo', body).subscribe(
      response => {
        console.log('Request successful:', response);
      },
    );
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
        console.log('Request successful:',);
        this.dataService.changeConnection(new ConenctionWithCityId(response, this.selectedCityFrom.id, this.selectedCityTo.id));
        this.router.navigate(['/searched']);
      },
      error => {
        // Handle any errors that occurred during the request
        console.error('An error occurred:', error);
            if (error.error instanceof ErrorEvent) {
                // A network error occurred
                console.error('Network error:', error.error.message);
                this.snackBar.open('A network error occurred. Please check your internet connection.', undefined, { panelClass: ['red-snackbar'] });
            } else {
                // The backend returned an unsuccessful response code
                console.error(`Backend returned code ${error.status}, body was: ${error.error}`);
                this.snackBar.open(`An error occurred while processing your request. Please try again later.`, undefined, 
                { 
                  duration: 3000,
                  panelClass: ['red-snackbar'] });
            }
      }
    );
  }

  compareFn(city1: City, city2: City): boolean {
    return city1 && city2 ? city1.id === city2.id : city1 === city2;
  }  
}
