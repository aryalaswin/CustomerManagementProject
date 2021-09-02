import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './auth.interceptor';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';

import { NavbarComponent } from './navbar/navbar.component';
import{BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ToastrModule} from 'ngx-toastr';
import { AddFamilyComponent } from './add-family/add-family.component';
import { ViewCustomerComponent } from './view-customer/view-customer.component';
import { CustomerFamilyComponent } from './customer-family/customer-family.component';
import { EditFamilyComponent } from './edit-family/edit-family.component';
import { DatePipe } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CustomerListComponent,
    CreateCustomerComponent,
    UpdateCustomerComponent,
    NavbarComponent,
    AddFamilyComponent,
    ViewCustomerComponent,
    CustomerFamilyComponent,
    EditFamilyComponent
    
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
   
    ToastrModule.forRoot({ timeOut: 3000,
    
    }),
   
    
  ],
  providers: [DatePipe,{provide:HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
