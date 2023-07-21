import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import { UserAuthService } from '../_services/user-auth.service';
import { Toast } from '../_model/toast.model';
import Swal from 'sweetalert2';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  formLogin!: FormGroup;
  userdata: any;

  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) {
    this.initialize();
  }

  ngOnInit(): void { }

  initialize() {
    this.formLogin = new FormGroup({
      userName: new FormControl('', [Validators.required]),
      userPassword: new FormControl('', Validators.required),
    });
  }

  login() {
    this.userService.login(this.formLogin.value).subscribe({
      next: (response: any) => {
        const role = response.user.userRole[0].roleName;

        this.userAuthService.setRole(role);
        this.userAuthService.setUserName(response.user.userName);
        this.userAuthService.setToken(response.jwtToken);

        if (role === 'Admin') {
          this.router.navigate(['/admin']);
        } else if (role === 'Staff') {
          this.router.navigate(['/staff']);
        } else {
          this.router.navigate(['/customer']);
        }

        Toast.fire({
          icon: 'success',
          title: 'Signed in successfully',
        });
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.status);

        if (error.status == 500) {
          Swal.fire(
            'Invalid Credentials . . .',
            'Login with valid credentials',
            'error'
          );
        }
        else if (error.status == 401 && this.userService.getIsCustomer()) {
          Swal.fire(
            'User not found . . .',
            'Create an account',
            'question'
          );
          this.router.navigate(['/register'])
        }
        else if (error.status == 401 && !this.userService.getIsCustomer()) {
          Swal.fire(
            'User not found . . .',
            'Contact admin to create an account',
            'error'
          );
        }

        this.formLogin.reset();
      },
    });
  }

  get isCustomer(): boolean {
    return this.userService.getIsCustomer();
  }
}
