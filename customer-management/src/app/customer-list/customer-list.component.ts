import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  count = 0;
  customers: Customer[];

  flag = false
response:string
  message: string
  number = false
  constructor(public customerService: CustomerService, private router: Router, private toster: ToastrService) {
    (this.customerService.mes) ? setTimeout(() => {
      this.customerService.mes = false
    }, 2000) : null

  }
  ngOnInit(): void {

    this.getCustomers();

  }



  private getCustomers() {
    this.customerService.getCustomerList().subscribe(data => {

      this.customers = data;

      this.customers.forEach(value => {


        if (value.status == "ACTIVE" || value.status == "INACTIVE") {
          this.flag = true;


        }
        if (value.emailAddress == null || value.emailAddress == '') {
          value.emailAddress = "N/A"
        }

      });

    }, (error) => {
      console.log(error)
    }

    )

  }
  viewFamily(id: number, name:string) {
    this.customerService.customerName=name
    this.customerService.id = id;
    this.router.navigate(['view-family']);
  }
  
  updateCustomer(id: number) {
    this.customerService.id = id;
    this.router.navigate(['update-customer']);

  }
  statusChange(id: number) {
    this.customerService.statusChange(id).subscribe(data => {
      
      this.toster.success(data.message)
      this.getCustomers()
    })
  }
  statusDelete(id: number) {
    
    this.customerService.statusDelete(id).subscribe(data => {
      this.toster.info(data.message)
      console.log(data.message)

      this.getCustomers()
    
 
      

    },error=>{
      this.toster.error(error.error.error)
    })
  }
 



}
