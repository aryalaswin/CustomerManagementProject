import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = "http://localhost:8080"
  constructor(private http: HttpClient, private toster: ToastrService) { }

  generateToken(credentials: any) {
    return this.http.post(`${this.url}/token`, credentials)
  }
  loginUser(token: string) {
    sessionStorage.setItem("token", token);
    return true;
  }
  isLoggedIn() {
    let token = sessionStorage.getItem("token");
    if (token == undefined || token == '' || token == null) {
      return false;
    } else {
      return true;
    }
  }
  logout() {

sessionStorage.removeItem('token');

    return true;
  }
  getToken() {
    return sessionStorage.getItem("token")
  }


}
