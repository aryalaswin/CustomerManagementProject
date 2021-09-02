import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerFamily } from '../customer-family';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-view-customer',
  templateUrl: './view-customer.component.html',
  styleUrls: ['./view-customer.component.css']
})
export class ViewCustomerComponent implements OnInit {

  customerFamily: CustomerFamily[]
customerData:Customer
  id: number
  name:String
  family = false
  statusCheck=false
  constructor(public customerService: CustomerService, private route: ActivatedRoute,
    private router: Router) { }

    addFamily() {
     
      this.router.navigate(['add-family']);
    }
editFamily(){
  this.router.navigate(['edit-family']);
  
}


  ngOnInit(): void {

    this.customerService.getCustomerById().subscribe(data=>{
     if(data.status=="INACTIVE"){
this.statusCheck=true
     }
    })

    this.customerService.findCustomerFamily().subscribe(data => {

      this.customerFamily = data;
      if (this.customerFamily.length == 0) {
        this.family = true

      }
      this.customerFamily.forEach(value=>{
        if(value.relationPersonName==""||value.relationPersonName==null){
          value.relationPersonName="N/A"
        }
      })
     
     }
    , (error) => {
      console.log(error)
    }

    )}}

 
