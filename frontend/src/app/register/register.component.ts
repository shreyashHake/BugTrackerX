import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  [x: string]: any;
  registerForm!: FormGroup;

  passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).{6,}$/;

  constructor(
    private userService: UserService,
    private router: Router
  ) {  }

  initializeForm() {
    this.registerForm = new FormGroup({
      userName: new FormControl('', Validators.required),
      userPassword: new FormControl('', [Validators.required, Validators.minLength(6), Validators.pattern(this.passwordPattern)])
    });
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  register() {
    this.userService.registerUser(this.registerForm.value).subscribe({
      next: (response) => {
        const id = this.registerForm.value.userName;
        this.registerForm.reset();
        this.router.navigate(['/customer-profile', id]);
      },
      error: (err) => {
        console.log(err);
        window.alert("User name already exists");
      }
    })
  }
}
