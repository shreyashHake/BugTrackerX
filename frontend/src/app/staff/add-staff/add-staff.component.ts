import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StaffService } from 'src/app/_services/staff.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-add-staff',
  templateUrl: './add-staff.component.html',
  styleUrls: ['./add-staff.component.scss']
})
export class AddStaffComponent {
  registerForm!: FormGroup;

  passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).{6,}$/;

  constructor(
    private userService: UserService,
    private staffService: StaffService,
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
    this.initializeForm();

  }

  register() {
    this.staffService.registerStaff(this.registerForm.value).subscribe({
      next: (response) => {
        this.registerForm.reset();
        this.router.navigate(['/staff-handling']);
      },
      error: (err) => {
        console.log(err);
        window.alert("User name already exists");
      }
    })
  }

  public backToStaff() {
    this.router.navigate((["/staff-handling"]))
  }

  public getAll() {
    this.staffService.getAlluser().subscribe({
      next: (response) => {
        console.log(response)
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

}
