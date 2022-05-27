import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BankAccount } from 'src/app/model/bankAccount.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BankAccountService {

  constructor(private http:HttpClient) { }
  public getBankAccounts() : Observable<Array<BankAccount>>{
    return this.http.get<Array<BankAccount>>(environment.bachendHost+"/bankAccounts");
  } 
  public getBankAccount(id : string) : Observable<BankAccount>{
    return this.http.get<BankAccount>(environment.bachendHost+"/bankAccounts/"+id);
  } 
  public searchBankAccounts(keyword : string) : Observable<Array<BankAccount>>{
    return this.http.get<Array<BankAccount>>(environment.bachendHost+"/bankAccounts/search?keyword="+keyword);
  }
  public saveBankAccount(bankAccount: BankAccount) : Observable<BankAccount>{
    return this.http.post<BankAccount>(environment.bachendHost+"/bankAccounts", bankAccount);
  } 
  public deleteBankAccount(id : string) {
    return this.http.delete(environment.bachendHost+"/bankAccounts/"+id);
  } 
  public updateBankAccount(id : string, bankAccount : BankAccount) {
    return this.http.put(environment.bachendHost+"/bankAccounts/"+id, bankAccount);
  } 
}