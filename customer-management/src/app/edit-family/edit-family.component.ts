import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../customer';
import { CustomerFamily } from '../customer-family';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-edit-family',
  templateUrl: './edit-family.component.html',
  styleUrls: ['./edit-family.component.css']
})
export class EditFamilyComponent implements OnInit {
  cus: Customer = new Customer()
  relationArray: CustomerFamily[] = []
  customerFamily: CustomerFamily[]
  married = false;
  errorPresent = false
  error: string
  editFamily: FormGroup
  constructor(public customerService: CustomerService, private formBuilder: FormBuilder, private router: Router, private toaster: ToastrService) {

    this.editFamily = this.formBuilder.group({
      fatherName: ["", [Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$')]],
      motherName: ["", [Validators.required, Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$')]],
      grandfatherName: ["", [Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$')]],
      spouseName: [""]

    })

  }

  ngOnInit(): void {
    this.customerService.getCustomerById().subscribe(data => {

      if (data.status.startsWith('I')) {
        // this.errorPresent=true
      }

      if (data.maritalStatus.startsWith('M')) {
        this.married = true;
        this.editFamily.controls['spouseName'].setValidators([Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$')]);

      }
    })

    this.customerService.findCustomerFamily().subscribe(data => {
      this.customerFamily = data
      this.editFamily.controls['fatherName'].setValue(this.customerFamily[0].relationPersonName)
      this.editFamily.controls['motherName'].setValue(this.customerFamily[1].relationPersonName)
      this.editFamily.controls['grandfatherName'].setValue(this.customerFamily[2].relationPersonName)
      if (this.married) {
        this.editFamily.controls['spouseName'].setValue(this.customerFamily[3].relationPersonName)
      }

    })
  }
  get editForm() {
    return this.editFamily.controls;
  }
  saveCustomerFamily() {

    let relationArrays: CustomerFamily[] = []
    let fath: CustomerFamily = {
      relationship: "Father",
      relationPersonName: this.editFamily.controls['fatherName'].value
    }
    relationArrays.push(fath);
    let moth: CustomerFamily = {
      relationship: "Mother",
      relationPersonName: this.editFamily.controls['motherName'].value
    }
    relationArrays.push(moth);

    let grandFath: CustomerFamily = {
      relationship: "Grandfather",
      relationPersonName: this.editFamily.controls['grandfatherName'].value
    }
    relationArrays.push(grandFath);

    if (this.married) {
      let spou: CustomerFamily = {
        relationship: "Spouse",
        relationPersonName: this.editFamily.controls['spouseName'].value   
      }
      relationArrays.push(spou);
    }

    // let father = new CustomerFamily();
    // father.relationship = "Father"
    // father.relationPersonName = this.editFamily.controls['fatherName'].value
    // this.relationArray.push(father)
    // let mother = new CustomerFamily();
    // mother.relationship = "Mother"
    // mother.relationPersonName = this.editFamily.get('motherName').value
    // this.relationArray.push(mother)
    // let grand = new CustomerFamily();
    // grand.relationship = "Grandfather"
    // grand.relationPersonName = this.editFamily.get('grandfatherName').value
    // this.relationArray.push(grand)
    // if (this.married) {
    //   let spouse = new CustomerFamily();
    //   spouse.relationship = "Spouse"
    //   spouse.relationPersonName = this.editFamily.get('spouseName').value
    //   this.relationArray.push(spouse)
    // }

    this.customerService.customerFamilyEdit(relationArrays).subscribe(data => {
      this.router.navigate(['view-family']).then(() => {
        this.toaster.success('Customer Family is Edited successfully', 'Success!!');
      })
    }
      , (error) => {
        this.errorPresent = true
        this.error = error.error.error
        this.relationArray = []
        relationArrays=[]
        this.toaster.error('Update unsuccess');

      })


  }
}
