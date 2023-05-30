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
  registerForm!: FormGroup;

  passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).{6,}$/;

  constructor(
    private userService: UserService,
    private router: Router
  ) {
    this.initializeForm();
  }

  initializeForm() {
    this.registerForm = new FormGroup({
      userName: new FormControl('', Validators.required),
      userFirstName: new FormControl('', [Validators.required]),
      userLastName: new FormControl('', [Validators.required]),
      userPassword: new FormControl('', [Validators.required, Validators.minLength(6), Validators.pattern(this.passwordPattern)])
    });
  }

  ngOnInit(): void {

  }

  register() {
    console.log(this.registerForm);
    this.userService.register(this.registerForm.value).subscribe({
      next: (response) => {
        this.registerForm.reset();
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.log(err);
        window.alert("User name already exists");
      }
    })
  }
}
