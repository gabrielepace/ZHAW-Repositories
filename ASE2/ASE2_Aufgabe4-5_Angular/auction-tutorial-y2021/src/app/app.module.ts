
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuctionListComponent } from './auction/auction-list/auction-list.component';
import { MouseEventDisplayComponent } from './auction/mouse-event-display/mouse-event-display.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuctionDataService } from './auction/auction-data.service';
import { AuctionListDetailComponent } from './auction/auction-list-detail/auction-list-detail.component';
import { AngularDateHttpInterceptorService } from './shared/angular-date-http-interceptor.service';
import { AuctionDetailComponent } from './auction/auction-detail/auction-detail.component';
import { HelperService } from './shared/helper.service';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    AuctionListComponent,
    MouseEventDisplayComponent,
    AuctionListDetailComponent,
    AuctionDetailComponent,
    HomeComponent,
    NavBarComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AngularDateHttpInterceptorService,
      multi: true,
    },
    AuctionDataService,
    HelperService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
