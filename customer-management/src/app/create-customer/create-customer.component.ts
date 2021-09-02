import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, PatternValidator, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { max } from 'moment-timezone';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../customer';
import { CustomerFamily } from '../customer-family';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {
  // customer: Customer = new Customer();
  serverError = []
  myForm: FormGroup
  error: string
  errorPresent = false
date:string
  constructor(private customerService: CustomerService, private router: Router, public datePip:DatePipe, private toster: ToastrService, private formBuilder: FormBuilder) {
    this.myForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.pattern("^[a-zA-Z]{3,15}$")]],
      middleName: ['', [Validators.pattern("^$|[a-zA-Z]{3,15}$")]],
      lastName: ['', [Validators.required, Validators.pattern('^[a-zA-Z]{3,15}$')]],
      dateOfBirth: [this.date,[Validators.required]],
      citizenshipNumber: ['', [Validators.required, Validators.pattern("^[0-9]{2,10}(?:[\-\/][0-9]{2,5}){0,3}$")]],
      emailAddress: ['', [Validators.pattern("[\\w-\\.]{1,10}@([\\w-]{2,10}\\.)+[\\w-]{2,4}$")]],
      address: ['', [Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?:[\,]{0,1} +[a-zA-Z]{3,10}){0,3}$')]],
      mobileNumber: ['', [Validators.pattern("98+[0-9]{8}$")]],
      gender: ['1',Validators.required],
      maritalStatus: ['',[Validators.required]],

    })
   
      const dateFormat = 'yyyy-MM-dd';
      this.date = datePip.transform(
        new Date().setDate(new Date().getDate()-1),
        dateFormat
      );

  }

  ngOnInit(): void {
    console.log(this.date)
    this.myForm.controls.gender.setValue("Male");
    this.myForm.controls.maritalStatus.setValue("Married");
  }
  saveCustomer() {
    this.customerService.createCustomer(this.myForm.value).subscribe(data => {
      this.myForm = data

      this.customerService.message = "Customer Inserted successfully "
      this.customerService.mes = true;

      this.router.navigate(['/customers']).then(() => {
        this.toster.success('Customer is inserted successfully', 'Success!!');
      })


    }
      , (error: any) => {
        this.errorPresent = true
        this.error = error.error.error
        //this.error = "Insert Unsuccess"
        this.toster.error("insert unsuccess")
       
      })



  }


  get registerFormControl() {
    return this.myForm.controls;
  }

  onSubmit() {
    this.saveCustomer();

  }
}
