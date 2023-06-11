import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ConenctionWithCityId } from './models/connection';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private connectionSource = new BehaviorSubject(new ConenctionWithCityId([], "", ""));
  private selectedConnectionIdSource = new BehaviorSubject<string>("");
  private selectedDateSource = new BehaviorSubject<string>("");


  currentConnection = this.connectionSource.asObservable();
  selectedConnectionId = this.selectedConnectionIdSource.asObservable();
  selectedDate = this.selectedDateSource.asObservable();


  constructor() { }

  changeConnection(connection: ConenctionWithCityId) {
    this.connectionSource.next(connection)
  }

  changeSelectedConnectionId(connectionId: string) {
    this.selectedConnectionIdSource.next(connectionId);
  }
  changeSelectedDate(date: string) {
    this.selectedDateSource.next(date);
  }
}