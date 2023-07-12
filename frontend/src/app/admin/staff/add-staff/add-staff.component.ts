import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Register } from 'src/app/_model/register.model';
import { StaffProfile } from 'src/app/_model/staffProfile.model';
import { StaffService } from 'src/app/_services/staff.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-add-staff',
  templateUrl: './add-staff.component.html',
  styleUrls: ['./add-staff.component.scss']
})
export class AddStaffComponent {
  registerForm!: FormGroup;
  registerStaff !: Register;

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
      staffName: new FormControl('', Validators.required),
      staffEmail: new FormControl('', [Validators.required]),
      staffPhone: new FormControl('', [Validators.required]),
      userPassword: new FormControl('', [Validators.required, Validators.minLength(6), Validators.pattern(this.passwordPattern)])
    });

    this.registerStaff = { userName: '', userPassword: '' };
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  register() {

    this.registerStaff.userName = this.registerForm.value.staffEmail;
    this.registerStaff.userPassword = this.registerForm.value.userPassword;

    console.log(this.registerStaff)


    this.userService.registerStaff(this.registerStaff).subscribe({
      next: (response) => {
        this.createStaffProfile();

        this.registerForm.reset();

        this.router.navigate(['/admin/staff-handling']);
      },
      error: (err) => {
        console.log(err);
        window.alert("User name already exists");
      }
    })
  }


  public backToStaff() {
    this.router.navigate((["/admin/staff-handling"]))
  }



  public createStaffProfile() {

    console.log("reachedd here" + this.registerForm.value);


    const profile: StaffProfile = {
      staffProfileId: 0,
      staffName: this.registerForm.value.staffName,
      staffPhone: this.registerForm.value.staffPhone,
      staffEmail: this.registerForm.value.staffEmail,
      isActive: true,
      user: {
        userName: this.registerForm.value.staffEmail
      }
    }


    this.staffService.createStaffProfile(profile).subscribe({
      next: (res) => {
        console.log(res);
      },
      error: (err) => {
        console.log(err);

      }
    })
  }

}
