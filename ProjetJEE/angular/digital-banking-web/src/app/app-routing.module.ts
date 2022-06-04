import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomersComponent } from './templates/customers/customers.component';
import { LoginComponent } from './templates/login/login.component';
import { NotFoundComponent } from './templates/not-found/not-found.component';
import { BankAccountComponent } from './templates/bank-account/bank-account.component';
import { AccountOperationComponent } from './templates/accountOperation/account-operation/account-operation.component';
import { DefaultComponent } from './templates/default/default.component';

const routes: Routes = [
  
  {
    path:'login',
    component:LoginComponent,
  },
  {
    path:'',
    redirectTo:'login', 
    pathMatch : 'full'
  },
  {path : "customers", component : CustomersComponent},
  {path:'home',component: DefaultComponent},
  {path : "customers", component : CustomersComponent},
  {path : "bank-account/:id", component : BankAccountComponent},
  {path : "account-operation/:id", component : AccountOperationComponent},
  {path: '**',component: NotFoundComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
