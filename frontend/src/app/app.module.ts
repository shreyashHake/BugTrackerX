import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './_auth/auth.guard';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { UserService } from './_services/user.service';
import { RegisterComponent } from './register/register.component';
import { CustomerComponent } from './customer/customer.component';
import { StaffComponent } from './staff/staff.component';
import { LoginLandingComponent } from './login/login-landing/login-landing.component';
import { StaffHandlingComponent } from './admin/staff/staff-handling/staff-handling.component';
import { AddStaffComponent } from './admin/staff/add-staff/add-staff.component';
import { EditStaffComponent } from './admin/staff/edit-staff/edit-staff.component';
import { ProjectComponent } from './customer/project/project.component';
import { CutomerProfileComponent } from './customer/cutomer-profile/cutomer-profile.component';
import { ViewProjectComponent } from './admin/view-project/view-project.component';
import { AddProjectComponent } from './customer/project/add-project/add-project.component';
import { EditProjectComponent } from './customer/project/edit-project/edit-project.component';
import { BugComponent } from './customer/bug/bug.component';
import { AddBugComponent } from './customer/bug/add-bug/add-bug.component';
import { CustomersComponent } from './admin/customers/customers.component';
import { CustomerPanelComponent } from './admin/customers/customer-panel/customer-panel.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    HomeComponent,
    AdminComponent,
    LoginComponent,
    HeaderComponent,
    ForbiddenComponent,
    RegisterComponent,
    CustomerComponent,
    StaffComponent,
    LoginLandingComponent,
    StaffHandlingComponent,
    AddStaffComponent,
    EditStaffComponent,
    ProjectComponent,
    CutomerProfileComponent,
    ViewProjectComponent,
    AddProjectComponent,
    EditProjectComponent,
    BugComponent,
    AddBugComponent,
    CustomersComponent,
    CustomerPanelComponent,
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
