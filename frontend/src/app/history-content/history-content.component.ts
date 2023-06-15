import { Component, Input ,OnInit} from '@angular/core';
import { BuyTickets } from '../models/request-models/buyTicket';
import { DataService } from '../data.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { TicketInfo } from '../models/ticketInfo';
import { User } from '../models/user';





@Component({
  selector: 'app-history-content',
  templateUrl: './history-content.component.html',
  styleUrls: ['./history-content.component.css']
})
export class HistoryContentComponent {
  public myAngularxQrCode: string = "";
  user: User;
  tickets: TicketInfo[];

  constructor (private http: HttpClient, private dataService: DataService) {
    this.dataService.userInfo.subscribe(userInfo => {
      this.user = userInfo; 
    });
  }

  ngOnInit(): void {
    const body = { username: this.user.email }; 
    this.http.post<TicketInfo[]>('http://localhost:8080/history', body).subscribe(
      response => {
        this.tickets= response.map(item => new TicketInfo(
          item.routeName,
          item.startCityName,
          item.endCityName,
          item.price,
          new Date(item.departureTime),
          item.qrCode  ));
        console.log('Request successful:', response);
      },
    );
  }

  generateQRCode(ticket: TicketInfo) {
    ticket.generatedQRCode = ticket.qrCode;
  }
}
