import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/model/customer.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  headers: any;
  constructor(private http:HttpClient) {
    this.headers = {'Authorization': 'Bearer ' + localStorage.getItem("accessToken")};
   }
  public getCustomers() : Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/customers", {headers: this.headers});
  } 
  public getCustomer(id : number) : Observable<Customer>{
    return this.http.get<Customer>(environment.backendHost+"/customers/"+id, {headers: this.headers});
  } 
  public searchCustomers(keyword : string) : Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/customers/search?keyword="+keyword, {headers: this.headers});
  }
  public saveCustomer(customer : Customer) : Observable<Customer>{
    return this.http.post<Customer>(environment.backendHost+"/customers", customer, {headers: this.headers});
  } 
  public deleteCustomer(id : number) {
    return this.http.delete(environment.backendHost+"/customers/"+id, {headers: this.headers});
  } 
  public updateCustomer(customer : Customer) {
    return this.http.put(environment.backendHost+"/customers/"+customer.id, customer, {headers: this.headers});
  } 
}
