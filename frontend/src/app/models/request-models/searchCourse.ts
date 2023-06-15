export class SearchCourse {
    startCity: string;
    endCity: string;
    departureTime: Date;
    
    constructor(startCity: string, endCity: string, departureTime: Date) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.departureTime = departureTime;
    }
  }
  