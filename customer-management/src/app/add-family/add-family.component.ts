import { variable } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../customer';
import { CustomerFamily } from '../customer-family';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-add-family',
  templateUrl: './add-family.component.html',
  styleUrls: ['./add-family.component.css']
})

export class AddFamilyComponent implements OnInit {
  cus: Customer = new Customer()
  relationArray: CustomerFamily[] = []
  
  sampleArrayName = []
  addFamily: FormGroup
  married = false;
  constructor(public customerService: CustomerService, private route: ActivatedRoute, private router: Router, private toaster: ToastrService, private formBuilder: FormBuilder) {

    this.addFamily = this.formBuilder.group({
      fatherName: ["", [Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$')]],
      motherName: ["", [Validators.required,  Validators.pattern('[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$')]],
      grandfatherName: ["", [Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$')]],
      spouseName: [""]

    })





  }


  get addForm() {
    return this.addFamily.controls;
  }


  ngOnInit(): void {
    this.customerService.getCustomerById().subscribe(data => {
      this.cus = data;
      if (this.cus.maritalStatus.startsWith('M')) {
        this.addFamily.controls['spouseName'].setValidators([Validators.required, Validators.pattern('[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$')]);

        this.married = true;
      }
    })


  }


  saveCustomerFamily() {
    let relationArrays: CustomerFamily[] = []
    let fath: CustomerFamily = {
      relationship: "Father",
      relationPersonName: this.addFamily.controls['fatherName'].value
    }
    relationArrays.push(fath);
    let moth: CustomerFamily = {
      relationship: "Mother",
      relationPersonName: this.addFamily.controls['motherName'].value
    }
    relationArrays.push(moth);
    let grandFath: CustomerFamily = {
      relationship: "Grandfather",
      relationPersonName: this.addFamily.controls['grandfatherName'].value
    }
    relationArrays.push(grandFath);
    if (this.married) {
      let spou: CustomerFamily = {
        relationship: "Spouse",
        relationPersonName: this.addFamily.controls['spouseName'].value
        
      }
      relationArrays.push(spou);
    }
    
  

    // let father = new CustomerFamily();
    // father.relationship = "Father"
    // father.relationPersonName = this.addFamily.controls['fatherName'].value
    // this.relationArray.push(father)
    // let mother = new CustomerFamily();
    // mother.relationship = "Mother"
    // mother.relationPersonName = this.addFamily.get('motherName').value
    // this.relationArray.push(mother)
    // let grand = new CustomerFamily();
    // grand.relationship = "Grandfather"
    // grand.relationPersonName = this.addFamily.get('grandfatherName').value
    // this.relationArray.push(grand)
    // if (this.married) {
    //   let spouse = new CustomerFamily();
    //   spouse.relationship = "Spouse"
    //   spouse.relationPersonName = this.addFamily.get('spouseName').value
    //   this.relationArray.push(spouse)
    // }
    this.customerService.addCustomerFamily(relationArrays).subscribe(data => {
      this.router.navigate(['view-family']).then(() => {
        this.toaster.success('Customer Family is inserted', 'Success!!');
      })


    }
      , (error) => {
        console.log(error.error.error)
        this.toaster.error(error.error.error);

        relationArrays=[]

      })


  }
}
