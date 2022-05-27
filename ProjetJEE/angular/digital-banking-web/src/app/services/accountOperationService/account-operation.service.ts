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
 
  constructor(private http:HttpClient) { }
  public getAccountOperations(id : string) : Observable<Array<AccountOperation>>{
    return this.http.get<Array<AccountOperation>>(environment.bachendHost+"/bankAccount/accountOperations/"+id);
  } 
  public getAccountOperation(id : number) : Observable<AccountOperation>{
    return this.http.get<AccountOperation>(environment.bachendHost+"/accountOperations/"+id);
  } 
  public searchAccountOperations(keyword : string) : Observable<Array<AccountOperation>>{
    return this.http.get<Array<AccountOperation>>(environment.bachendHost+"/accountOperations/search?keyword="+keyword);
  }
  public debit(bankAccount : BankAccount) : Observable<AccountOperation>{
    return this.http.post<AccountOperation>(environment.bachendHost+"/debit", bankAccount);
  }
  public credit(bankAccount : BankAccount) : Observable<AccountOperation>{
    return this.http.post<AccountOperation>(environment.bachendHost+"/credit", bankAccount);
  } 
  public transfer(idSource : string, accountOperation : AccountOperation) : Observable<AccountOperation>{
    return this.http.put<AccountOperation>(environment.bachendHost+"/transfert/"+idSource,accountOperation);
  } 
  public deleteAccountOperation(id : number) {
    return this.http.delete(environment.bachendHost+"/accountOperations/"+id);
  } 
  public updateAccountOperation(id : number, accountOperation : AccountOperation) {
    return this.http.put(environment.bachendHost+"/accountOperations/"+id, accountOperation);
  }
} 