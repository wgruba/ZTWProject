import { Component } from '@angular/core';
import { GoogleApiService, UserInfo } from '../google-api.service';
import { User } from '../models/user';
import { DataService } from '../data.service';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  user: User;


  constructor(private readonly googleApi: GoogleApiService, private dataService: DataService) {
    this.dataService.userInfo.subscribe(userInfo => this.user = userInfo);
  }

  logout() {
    this.googleApi.signOut()
  }
}
