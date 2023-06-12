import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ConenctionWithCityId } from './models/connection';
import { PlaceInfo, PlaceInfoWithCourseStops } from './models/placeInfo';


@Injectable({
  providedIn: 'root'
})
export class DataService {
  private connectionSource = new BehaviorSubject(new ConenctionWithCityId([], "", ""));
  private selectedConnectionIdSource = new BehaviorSubject<string>("");
  private selectedDateSource = new BehaviorSubject<string>("");
  private placeInfoSource = new BehaviorSubject(new PlaceInfoWithCourseStops([],'','',''));
  private routeNameSource = new BehaviorSubject<string>("");
  private userIdSource = new BehaviorSubject<string>("");


  currentConnection = this.connectionSource.asObservable();
  selectedConnectionId = this.selectedConnectionIdSource.asObservable();
  selectedDate = this.selectedDateSource.asObservable();
  placeInfo = this.placeInfoSource.asObservable();
  routeName = this.routeNameSource.asObservable();
  userId  = this.userIdSource.asObservable();


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
  changePlaceInfoDate(paceInfoWithCourseStops: PlaceInfoWithCourseStops) {
    this.placeInfoSource.next(paceInfoWithCourseStops);
  }
  changeRouteName(routeName: string) {
    this.routeNameSource.next(routeName);
  }
  changeUserId(userId: string) {
    this.userIdSource.next(userId);
  }
}