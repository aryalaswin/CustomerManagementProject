import { Injectable } from '@angular/core';
import {  catchError } from 'rxjs/operators'
import { Observable, throwError } from 'rxjs';
import { Customer } from './customer';
import { HttpClient } from '@angular/common/http';
import { CustomerFamily } from './customer-family';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
message:string
mes=false
id:number
customerName:string
ids:string
  private baseUrl="http://localhost:8080/customers";

  constructor(private httpCLient:HttpClient,public toaster:ToastrService) { }
getCustomerList():Observable<Customer[]>{
  return this.httpCLient.get<Customer[]>(`${this.baseUrl}/get`);
}
createCustomer(customer:Customer):Observable<any>{
  return this.httpCLient.post(`${this.baseUrl}/post`,customer)
}

updateCustomer(customer:Customer):Observable<Object>{
  return this.httpCLient.put(`${this.baseUrl}/${this.id}`,customer)
}
getCustomerById():Observable<Customer>{
  sessionStorage.setItem("customer",this.customerName)
  return this.httpCLient.get<Customer>(`${this.baseUrl}/getById/${this.id}`);
}

statusChange(id: number): Observable<any>{
 
  return this.httpCLient.put(`${this.baseUrl}/status/${id}`,"Change status of customer with id "+id);
}
statusDelete(id: number): Observable<any>{
  return this.httpCLient.delete(`${this.baseUrl}/statusDelete/${id}`);
}

addCustomerFamily(customerFamily:any):Observable<any>{
  return this.httpCLient.post(`${this.baseUrl}/customerFamily/${this.id}`,customerFamily)
}
findCustomerFamily():Observable<CustomerFamily[]>{

  
  return this.httpCLient.get<CustomerFamily[]>(`${this.baseUrl}/customerFamily/${this.id}`)
}
customerFamilyEdit(customerFamily:CustomerFamily[]):Observable<any>{
  return this.httpCLient.put(`${this.baseUrl}/customerFamilyEdit/${this.id}`,customerFamily)
}

handleErrors()
{
  return throwError("Server Error! Insert Unsuccess")
}

}
