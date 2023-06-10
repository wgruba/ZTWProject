import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ConenctionWithCityId } from './models/connection';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private connectionSource = new BehaviorSubject(new ConenctionWithCityId([], "", ""));
  currentConnection = this.connectionSource.asObservable();

  constructor() { }

  changeConnection(connection: ConenctionWithCityId) {
    this.connectionSource.next(connection)
  }
}