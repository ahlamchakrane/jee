import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/model/customer.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  
  constructor(private http:HttpClient) { }
  public getCustomers() : Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.bachendHost+"/customers");
  } 
  public getCustomer(id : number) : Observable<Customer>{
    return this.http.get<Customer>(environment.bachendHost+"/customers/"+id);
  } 
  public searchCustomers(keyword : string) : Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.bachendHost+"/customers/search?keyword="+keyword);
  }
  public saveCustomer(customer : Customer) : Observable<Customer>{
    return this.http.post<Customer>(environment.bachendHost+"/customers", customer);
  } 
  public deleteCustomer(id : number) {
    return this.http.delete(environment.bachendHost+"/customers/"+id);
  } 
  public updateCustomer(customer : Customer) {
    return this.http.put(environment.bachendHost+"/customers/"+customer.id, customer);
  } 
}
