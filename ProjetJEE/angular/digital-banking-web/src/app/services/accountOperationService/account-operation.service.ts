import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AccountOperation } from 'src/app/model/accountOperation.model';
import { BankAccount } from 'src/app/model/bankAccount.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountOperationService {
  headers: any;

  constructor(private http:HttpClient) { 
    this.headers = {'Authorization': 'Bearer ' + localStorage.getItem("accessToken")};
  }
  public getAccountOperations(id : string) : Observable<Array<AccountOperation>>{
    return this.http.get<Array<AccountOperation>>(environment.backendHost+"/bankAccount/accountOperations/"+id, {headers: this.headers});
  } 
  public getAccountOperation(id : number) : Observable<AccountOperation>{
    return this.http.get<AccountOperation>(environment.backendHost+"/accountOperations/"+id, {headers: this.headers});
  } 
  public searchAccountOperations(keyword : string) : Observable<Array<AccountOperation>>{
    return this.http.get<Array<AccountOperation>>(environment.backendHost+"/accountOperations/search?keyword="+keyword, {headers: this.headers});
  }
  public debit(bankAccount : BankAccount) : Observable<AccountOperation>{
    return this.http.post<AccountOperation>(environment.backendHost+"/debit", bankAccount, {headers: this.headers});
  }
  public credit(bankAccount : BankAccount) : Observable<AccountOperation>{
    return this.http.post<AccountOperation>(environment.backendHost+"/credit", bankAccount, {headers: this.headers});
  } 
  public transfer(idSource : string, accountOperation : AccountOperation) : Observable<AccountOperation>{
    return this.http.put<AccountOperation>(environment.backendHost+"/transfert/"+idSource,accountOperation, {headers: this.headers});
  } 
  public deleteAccountOperation(id : number) {
    return this.http.delete(environment.backendHost+"/accountOperations/"+id, {headers: this.headers});
  } 
  public updateAccountOperation(id : number, accountOperation : AccountOperation) {
    return this.http.put(environment.backendHost+"/accountOperations/"+id, accountOperation, {headers: this.headers});
  }
} 