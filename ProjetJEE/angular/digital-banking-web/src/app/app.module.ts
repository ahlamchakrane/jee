import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxPaginationModule } from 'ngx-pagination';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { LoginComponent } from './templates/login/login.component';
import { NotFoundComponent } from './templates/not-found/not-found.component';
import { DefaultModule } from './model/default/default.module';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NotFoundComponent,
  ],
  imports: [
    DefaultModule,
    BrowserModule,
    AppRoutingModule,
    NgxSpinnerModule
  ],
  exports:[
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
