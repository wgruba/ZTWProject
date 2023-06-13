import { Component, Input } from '@angular/core';
import { BuyTickets } from '../models/request-models/buyTicket';


@Component({
  selector: 'app-history-content',
  templateUrl: './history-content.component.html',
  styleUrls: ['./history-content.component.css']
})
export class HistoryContentComponent {
  public myAngularxQrCode: string = "";
  tickets =  [new BuyTickets("sdasd","sadasda",["sad"],"sdaa","sdadaa",0),new BuyTickets("sdasd2","sadasda2",["sad2"],"sdaa2","sdadaa2",0)]

  constructor () {
  }

  generateQRCode(ticket: BuyTickets) {
    this.myAngularxQrCode = ticket.userId;
  }
}
