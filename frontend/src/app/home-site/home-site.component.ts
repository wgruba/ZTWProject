import { Component } from '@angular/core';
import { GoogleApiService, UserInfo } from '../google-api.service';


@Component({
  selector: 'app-home-site',
  templateUrl: './home-site.component.html',
  styleUrls: ['./home-site.component.css']
})
export class HomeSiteComponent {

  title = 'angular-google-oauth-example';

  mailSnippets: string[] = []
  userInfo?: UserInfo

  constructor(private readonly googleApi: GoogleApiService) {
    googleApi.userProfileSubject.subscribe( info => {
      this.userInfo = info
    })
  }

  isLoggedIn(): boolean {
    return this.googleApi.isLoggedIn()
  }
}
