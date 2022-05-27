import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, Injectable, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgxSpinnerModule } from 'ngx-spinner';
import { NavbarComponent } from 'src/app/navbar/navbar.component';
import { AccountOperationComponent } from 'src/app/templates/accountOperation/account-operation/account-operation.component';
import { BankAccountComponent } from 'src/app/templates/bank-account/bank-account.component';
import { CustomersComponent } from 'src/app/templates/customers/customers.component';
import { DefaultComponent } from 'src/app/templates/default/default.component';
import { FooterComponent } from 'src/app/templates/footer/footer.component';

@NgModule({
  imports : [
    BrowserModule,
    HttpClientModule,
    NgxSpinnerModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule
  ],
  declarations: [
    NavbarComponent,
    FooterComponent,
    CustomersComponent,
    BankAccountComponent,
    AccountOperationComponent,
    DefaultComponent
  ],
  exports:[
    FormsModule,
    FooterComponent,
    NavbarComponent,
    ReactiveFormsModule,
    NgxPaginationModule,
  ],
  providers: [
    DefaultComponent,
  ],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class DefaultModule {
}
