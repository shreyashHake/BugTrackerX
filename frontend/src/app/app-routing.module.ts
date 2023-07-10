import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './_auth/auth.guard';
import { RegisterComponent } from './register/register.component';
import { CustomerComponent } from './customer/customer.component';
import { StaffComponent } from './staff/staff.component';
import { LoginLandingComponent } from './login/login-landing/login-landing.component';
import { StaffHandlingComponent } from './admin/staff/staff-handling/staff-handling.component';
import { AddStaffComponent } from './admin/staff/add-staff/add-staff.component';
import { EditStaffComponent } from './admin/staff/edit-staff/edit-staff.component';
import { CutomerProfileComponent } from './customer/cutomer-profile/cutomer-profile.component';
import { ProjectComponent } from './customer/project/project.component';
import { ViewProjectComponent } from './admin/view-project/view-project.component';
import { AddProjectComponent } from './customer/project/add-project/add-project.component';
import { CustomersComponent } from './admin/customers/customers.component';
import { CustomerPanelComponent } from './admin/customers/customer-panel/customer-panel.component';

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'login-landing', component: LoginLandingComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'forbidden', component: ForbiddenComponent },

  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
    { path: 'admin/customers', component: CustomersComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
    { path: 'admin/customers/customer_panel/:id', component: CustomerPanelComponent , canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'staff-handling', component: StaffHandlingComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'add-staff', component: AddStaffComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'edit-staff/:userName', component: EditStaffComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'view-project', component: ViewProjectComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },

  { path: 'customer', component: CustomerComponent, canActivate: [AuthGuard], data: { roles: ['Customer'] } },
  { path: 'customer-profile/:userName', component: CutomerProfileComponent},
  { path: 'project', component: ProjectComponent, canActivate: [AuthGuard], data: { roles: ['Customer'] } },
  { path: 'add-project', component: AddProjectComponent, canActivate: [AuthGuard], data: { roles: ['Customer'] } },

  { path: 'staff', component: StaffComponent, canActivate: [AuthGuard], data: { roles: ['Staff'] } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
