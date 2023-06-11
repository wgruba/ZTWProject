import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConnectionLookupComponent } from './connection-lookup/connection-lookup.component';
import { HttpClientModule } from '@angular/common/http';

import { PageConnectionComponent } from './page-connection/page-connection.component';
import { CourseSelectionComponent } from './course-selection/course-selection.component';
import { SeatSelectionComponent } from './seat-selection/seat-selection.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { MainComponent } from './main/main.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HomeSiteComponent } from './home-site/home-site.component';
import { SearchedSiteComponent } from './searched-site/searched-site.component';
import { RouterModule } from '@angular/router';
import { SearchedContentComponent } from './searched-content/searched-content.component';
import { SelectionSiteComponent } from './selection-site/selection-site.component';
import { SelectionContentComponent } from './selection-content/selection-content.component';
import { CartSiteComponent } from './cart-site/cart-site.component';
import { CartContentComponent } from './cart-content/cart-content.component';

@NgModule({
  declarations: [
    AppComponent,
    ConnectionLookupComponent,
    PageConnectionComponent,
    CourseSelectionComponent,
    SeatSelectionComponent,
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    MainComponent,
    SidebarComponent,
    HomeSiteComponent,
    SearchedSiteComponent,
    SearchedContentComponent,
    SelectionSiteComponent,
    SelectionContentComponent,
    CartSiteComponent,
    CartContentComponent,
  ],
  imports: [
    MatSnackBarModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RouterModule.forRoot([
      {path: '', component: HomeSiteComponent},
      {path: 'searched', component: SearchedSiteComponent},
      {path: 'select', component: SelectionSiteComponent},
      {path: 'cart', component: CartSiteComponent},
      {path: '', redirectTo: '/searched', pathMatch: 'full'},
      {path: '', redirectTo: '/select', pathMatch: 'full'},
      {path: '', redirectTo: '/cart', pathMatch: 'full'},
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
