import { Component } from '@angular/core';
import { ConnectionLookupComponent } from '../connection-lookup/connection-lookup.component';
import { ConenctionWithCityId, Connection} from '../models/connection';

@Component({
  selector: 'app-page-connection',
  templateUrl: './page-connection.component.html',
  styleUrls: ['./page-connection.component.css']
})
export class PageConnectionComponent {
  connections: ConenctionWithCityId = new ConenctionWithCityId([], "", "");
  showCourseSelection = false;

  showResult(connections: any) {
    // Save the submitted form data
    this.connections = connections;
    this.showCourseSelection = true;
  }
}

