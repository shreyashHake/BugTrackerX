import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { AuthGuard } from './_auth/auth.guard';
import { RegisterComponent } from './register/register.component';
import { CustomerComponent } from './customer/customer.component';
import { StaffComponent } from './staff/staff.component';
import { LoginLandingComponent } from './login-landing/login-landing.component';

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: '', component: HomeComponent },
  // { path: 'login', component: LoginComponent },
  { path: 'login', component: LoginLandingComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'customer', component: CustomerComponent, canActivate: [AuthGuard], data: { roles: ['Customer'] } },
  { path: 'staff', component: StaffComponent, canActivate: [AuthGuard], data: { roles: ['Staff'] } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
