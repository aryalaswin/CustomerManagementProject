import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn=false;
  
  constructor(public loginService:LoginService, private router:Router,private toster:ToastrService) { }

  ngOnInit(): void {
    if(this.loginService.isLoggedIn()){
      this.isLoggedIn=true;
    }else{
      this.isLoggedIn=false
    }
  }
logout(){
  this.toster.success("Logout SuccessFully")
  this.loginService.logout();
  window.location.href = "/login";
 
}

}
