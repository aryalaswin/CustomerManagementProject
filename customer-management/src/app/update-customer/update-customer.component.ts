import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})

export class UpdateCustomerComponent implements OnInit {
  editForm:FormGroup
  id: number
 error:any
 errorPresent=false
 date:string
  customer: Customer = new Customer();
  constructor(public customerService: CustomerService,
    private router: Router,private datePipe:DatePipe,public toster:ToastrService,private formBuilder:FormBuilder) {
   

      this.editForm=this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.pattern("^[a-zA-Z]{3,15}$")]],
      middleName: ['', [Validators.pattern("^$|[a-zA-Z]{3,15}$")]],
      lastName: ['', [Validators.required, Validators.pattern('^[a-zA-Z]{3,15}$')]],
      dateOfBirth: ['',Validators.required],
      citizenshipNumber: ['', [Validators.required, Validators.pattern("^[0-9]{2,10}(?:[\-\/][0-9]{2,5}){0,3}$")]],
      emailAddress: [, [Validators.pattern("[\\w-\\.]{1,10}@([\\w-]{2,10}\\.)+[\\w-]{2,4}$")]],
      address: ['', [Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?:[\,]{0,1} +[a-zA-Z]{3,10}){0,3}$')]],
      mobileNumber: ['',Validators.pattern("98+[0-9]{8}$")],
      gender: [''],
      maritalStatus: ['']
        
       
      })
      const dateFormat = 'yyyy-MM-dd';
      this.date = datePipe.transform(
        new Date().setDate(new Date().getDate()-1),
        dateFormat
      );
  }
  get registerFormControl() {
    return this.editForm.controls;
  }

  ngOnInit(): void {
    this.customerService.getCustomerById().subscribe(data => this.editForm.patchValue(data)
    , (error:any) => {
      this.errorPresent=true
      this.error=error
     
    })
  }
  onSubmit() {
    this.customerService.updateCustomer(this.editForm.value).subscribe(data => {
      this.gotoCustomerList(); 
      this.customerService.mes=true
    }
      , (error:any) =>{
        this.errorPresent=true
        this.error=error.error.error
        this.toster.error("Update Unsuccess")
        console.log(error.error.error)
       
      }
        );

  }

  gotoCustomerList() {
    this.customerService.message="CUSTOMER UPDATED SUCCESSFULLY"
    this.router.navigate(['/customers']).then(()=>{
      this.toster.success("Customer updated successfully")
    });
    
  }
}


