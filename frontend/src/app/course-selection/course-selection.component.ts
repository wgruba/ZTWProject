import { Component, Input } from '@angular/core';
import { ConenctionWithCityId, Connection } from '../models/connection';

@Component({
  selector: 'app-course-selection',
  templateUrl: './course-selection.component.html',
  styleUrls: ['./course-selection.component.css']
})
export class CourseSelectionComponent {
  @Input() connections: ConenctionWithCityId = new ConenctionWithCityId([], "", "");

  checkAvailability(connectionId: string) {
    console.log("this will be a request to backend !!! connectionId: " + connectionId);
  }
}
