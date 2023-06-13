import { Component, Input ,OnInit} from '@angular/core';
import { BuyTickets } from '../models/request-models/buyTicket';
import { DataService } from '../data.service';
import { HttpClient, HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-history-content',
  templateUrl: './history-content.component.html',
  styleUrls: ['./history-content.component.css']
})
export class HistoryContentComponent {
  public myAngularxQrCode: string = "";
  tickets =  [new BuyTickets("sdasd","sadasda",["sad"],"sdaa","sdadaa",0),new BuyTickets("sdasd2","sadasda2",["sad2"],"sdaa2","sdadaa2",0)]

  constructor (private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<string>('https://openidconnect.googleapis.com/v1/userinfo',).subscribe(
      response => {
        console.log('Request successful:', response);
      },
    );
  }

  generateQRCode(ticket: BuyTickets) {
    this.myAngularxQrCode = ticket.username;
  }
}
