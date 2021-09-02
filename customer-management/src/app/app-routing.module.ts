import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddFamilyComponent } from './add-family/add-family.component';
import { AuthGuard } from './auth.guard';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { CustomerFamilyComponent } from './customer-family/customer-family.component';


import { CustomerListComponent } from './customer-list/customer-list.component';
import { EditFamilyComponent } from './edit-family/edit-family.component';
import { LoginComponent } from './login/login.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { ViewCustomerComponent } from './view-customer/view-customer.component';

const routes: Routes = [
  { path: "customers",component:CustomerListComponent,canActivate:[AuthGuard]},
  { path: "login",component:LoginComponent,pathMatch:'full'  },
    {path:'', redirectTo:'login',pathMatch:'full'},
    {path:'create-customer', component:CreateCustomerComponent,canActivate:[AuthGuard]},
    {path:'edit-family', component:EditFamilyComponent,canActivate:[AuthGuard]},
    {path:'update-customer', component:UpdateCustomerComponent,canActivate:[AuthGuard]},
    {path:'view-family', component:ViewCustomerComponent,canActivate:[AuthGuard]},
    {path:'add-family', component:AddFamilyComponent,canActivate:[AuthGuard]}
   

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
