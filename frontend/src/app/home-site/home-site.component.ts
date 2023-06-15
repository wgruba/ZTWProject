import { Component, OnInit } from '@angular/core';
import { GoogleApiService, UserInfo } from '../google-api.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { DataService } from '../data.service';

@Component({
  selector: 'app-home-site',
  templateUrl: './home-site.component.html',
  styleUrls: ['./home-site.component.css']
})
export class HomeSiteComponent {
  title = 'angular-google-oauth-example';

  mailSnippets: string[] = []
  userInfo?: UserInfo

  constructor(private readonly googleApi: GoogleApiService,private http: HttpClient,private dataService: DataService) {
    googleApi.userProfileSubject.subscribe( info => {
      this.userInfo = info
        })
  }

  isLoggedIn(): boolean {
    return this.googleApi.isLoggedIn()
  }
}
