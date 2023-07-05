import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  formLogin!: FormGroup;
  userdata: any;

  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router) {
    this.initialize();
  }

  ngOnInit(): void {
  }

  initialize() {
    this.formLogin = new FormGroup({
      userName: new FormControl('', [Validators.required]),
      userPassword: new FormControl('', Validators.required)
    })
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
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  get isCustomer(): boolean {
    return this.userService.getIsCustomer();
  }
}
