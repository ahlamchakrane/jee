import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BankAccount } from 'src/app/model/bankAccount.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BankAccountService {
  headers: any;
  constructor(private http:HttpClient) {
    this.headers = {'Authorization': 'Bearer ' + localStorage.getItem("accessToken")};
   }
   public getBankAccounts() : Observable<Array<BankAccount>>{
    return this.http.get<Array<BankAccount>>(environment.backendHost+"/bankAccounts", {headers: this.headers});
  } 
  public getCustomerBankAccounts(id : number) : Observable<Array<BankAccount>>{
    return this.http.get<Array<BankAccount>>(environment.backendHost+"/customer/"+id+"/bankAccounts", {headers: this.headers});
  } 
  public getBankAccount(id : number) : Observable<BankAccount>{
    return this.http.get<BankAccount>(environment.backendHost+"/bankAccounts/"+id, {headers: this.headers});
  } 
  public searchBankAccounts(keyword : string) : Observable<Array<BankAccount>>{
    return this.http.get<Array<BankAccount>>(environment.backendHost+"/bankAccounts/search?keyword="+keyword, {headers: this.headers});
  }
  public saveBankAccount(bankAccount: BankAccount) : Observable<BankAccount>{
    return this.http.post<BankAccount>(environment.backendHost+"/bankAccounts", bankAccount, {headers: this.headers});
  } 
  public deleteBankAccount(id : number) {
    return this.http.delete(environment.backendHost+"/bankAccounts/"+id, {headers: this.headers});
  } 
  public updateBankAccount(id : number, bankAccount : BankAccount) {
    return this.http.put(environment.backendHost+"/bankAccounts/"+id, bankAccount, {headers: this.headers});
  } 
}