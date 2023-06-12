import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { PlaceInfo, PlaceInfoWithCourseStops } from '../models/placeInfo';
import { CheckAvailability } from '../models/request-models/checkAvailability';
import { DataService } from '../data.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


interface Bus {
  type: string;
  numRows: number;
  numCols: number;
}

interface Seat {
  x: number;
  y: number;
  width: number;
  height: number;
  isReserved: boolean;
  seatNumber: number;
  available: boolean;
}

@Component({
  selector: 'app-selection-content',
  templateUrl: './selection-content.component.html',
  styleUrls: ['./selection-content.component.css']
})

export class SelectionContentComponent implements OnInit{
  @ViewChild('canvas', { static: true })

  canvas: ElementRef<HTMLCanvasElement>;  
  private ctx: CanvasRenderingContext2D | null;
  private seats: Seat[] = [];
  selectedSeats: Seat[] = [];
  totalPrice: number = 0;
  pricePerSeat = 5.99;
  private readonly seatWidth = 20;
  private readonly seatHeight = 30;
  private readonly leftmargin = 30;
  private readonly topmargin = 150;
  private readonly padding = 5;
  private readonly aisleWidth = 20;
  selectedConnectionId: string;
  selectedDate: string;
  selectedBus: Bus = { type: 'none', numRows: 0, numCols: 0};
  seatsInfo: PlaceInfoWithCourseStops;


  canvasHeight = 100;
  canvasWidth = 100;

  constructor(private http: HttpClient, private dataService: DataService, private router: Router) {
  
  }

  ngOnInit(): void {
    this.ctx = this.canvas.nativeElement.getContext('2d');
    
    if(this.selectedBus.type = 'large'){
      this.canvas.nativeElement.width = 200
      this.canvas.nativeElement.height = 530;
    }
    else {
      this.canvas.nativeElement.width = 200
      this.canvas.nativeElement.height = 100;
    }

    this.dataService.selectedConnectionId.subscribe(connectionId => {
      this.selectedConnectionId = connectionId;
      console.log('Selected connectionId:', connectionId);
    });
    this.dataService.selectedDate.subscribe(date => {
      this.selectedDate = date; 
    });
    this.dataService.placeInfo.subscribe(placeInfo => {
      this.seatsInfo = placeInfo; 
    });

    if(this.seatsInfo.places.length > 25){
      this.selectedBus = { type: 'large', numRows: 8, numCols: 5};
    }
    else{
      this.selectedBus = { type: 'small', numRows: 5, numCols: 5};
    }
    
    const backgroundImage = new Image();
    backgroundImage.src = 'assets/bus-backgroung.png';
    backgroundImage.onload = () => {
      if(this.ctx){
        this.ctx.drawImage(backgroundImage, 0, 0);
      }
      this.generateSeats(this.selectedBus)
      this.drawSeats();
  }
}

  private generateSeats(bus: Bus): void {
    let seatNumber = 1;

    for (let row = 0; row < bus.numRows; row++) {
      for (let col = 0; col < 2; col++) {
        this.seats.push({
          x: this.leftmargin + this.padding + col * (this.seatWidth + this.padding),
          y: this.topmargin + this.padding * 2 + this.seatHeight + row * (this.seatHeight + this.padding),
          width: this.seatWidth,
          height: this.seatHeight,
          isReserved: false,
          seatNumber: this.seatsInfo.places[seatNumber - 1].nr,
          available: this.seatsInfo.places[seatNumber - 1].available
        });

        seatNumber++;

        this.seats.push({
          x: this.leftmargin + this.padding + this.aisleWidth + (col + 2) * (this.seatWidth + this.padding),
          y: this.topmargin + this.padding * 2 + this.seatHeight + row * (this.seatHeight + this.padding),
          width: this.seatWidth,
          height: this.seatHeight,
          isReserved: false,
          seatNumber: this.seatsInfo.places[seatNumber - 1].nr,
          available: this.seatsInfo.places[seatNumber - 1].available
        });

        seatNumber++;
      }
    }

    // add the 5-seat row at the back of the bus
    for (let col = 0; col < 5; col++) {
      this.seats.push({
        x: this.leftmargin + this.padding + col * (this.seatWidth + this.padding),
        y: this.topmargin + this.padding * 2 + this.seatHeight + this.selectedBus.numRows * (this.seatHeight + this.padding),
        width: this.seatWidth,
        height: this.seatHeight,
        isReserved: false,
        seatNumber: this.seatsInfo.places[seatNumber - 1].nr,
        available: this.seatsInfo.places[seatNumber - 1].available
      });
      seatNumber++;
    }
  }

  private drawSeats(): void {
    this.seats.forEach(seat => {
      if(this.ctx instanceof CanvasRenderingContext2D){
        this.ctx.fillStyle = seat.available ? (seat.isReserved ? 'red' : 'green') : 'grey';
        this.ctx.fillRect(seat.x, seat.y, seat.width, seat.height);

        // Draw seat number
        this.ctx.font = "10px Arial";
        this.ctx.fillStyle = "white";
        this.ctx.textAlign = "center";
        this.ctx.textBaseline = "middle";
        this.ctx.fillText(seat.seatNumber.toString(), seat.x + seat.width / 2, seat.y + seat.height / 2);
      }
    });
  }

  handleClick(event: MouseEvent): void {
    const rect = this.canvas.nativeElement.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
  
    const clickedSeat = this.seats.find(seat => 
      x >= seat.x && x <= seat.x + seat.width && 
      y >= seat.y && y <= seat.y + seat.height);
  
    if (clickedSeat) {
      if (clickedSeat.available) {
        if (clickedSeat.isReserved) {
        const index = this.selectedSeats.indexOf(clickedSeat);
        if (index > -1) {
          this.selectedSeats.splice(index, 1);
        }
        this.totalPrice -= this.pricePerSeat;
      } else {

        this.selectedSeats.push(clickedSeat);
        this.totalPrice += this.pricePerSeat;
      }
      
      clickedSeat.isReserved = !clickedSeat.isReserved;
      this.drawSeats();
      }
      
    }
  }

  getSelectedSeats(): string[] {
    return this.seatsInfo.places.filter(place => place.isSelected).map(place => place.placeId);
  }

  buyTickets(): void {
    for (let place = 0; place < this.seatsInfo.places.length; place++) {
    this.seatsInfo.places[this.selectedSeats[place].seatNumber - 1].isSelected = true;
    console.log(this.getSelectedSeats());
    this.router.navigate(['/cart']);
  }
}
}
