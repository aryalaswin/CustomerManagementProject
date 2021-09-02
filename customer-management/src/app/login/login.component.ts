import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  disable:boolean
  count=0
  error:string
  loginForm:FormGroup
errorPresent=false
 
  constructor(private loginService: LoginService, private formBuilder:FormBuilder) {
   this.loginForm= this.formBuilder.group({
    username:['',[Validators.required]],
    password:['',[Validators.required]]
   })
   }


  onSubmit() {
    this.count=this.count+1;
    if(this.count==3){
      this.disable=true;
    }
    
  
      this.loginService.generateToken(this.loginForm.value).subscribe(
        (response: any) => {
        
          console.log(response.token);
          this.loginService.loginUser(response.token);
          window.location.href = "/customers";
        },
        error => {
          this.errorPresent = true
          this.error =error.error.error
       

        }
      )
    } 
  }


