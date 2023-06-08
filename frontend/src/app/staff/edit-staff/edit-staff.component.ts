import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StaffService } from 'src/app/_services/staff.service';
import { User } from 'src/app/_model/user.model';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-edit-staff',
  templateUrl: './edit-staff.component.html',
  styleUrls: ['./edit-staff.component.scss']
})
export class EditStaffComponent implements OnInit {
  editForm: FormGroup;
  user!: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private staffService: StaffService
  ) {
    this.editForm = this.formBuilder.group({
      userName: ['', Validators.required],
      userFirstName: ['', Validators.required],
      userLastName: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const userName = params['userName'];
      this.getUserDetails(userName);
    });
  }

  public getUserDetails(userName: string): void {
    this.staffService.getUserByUserName(userName).subscribe({
      next: (user: User) => {
        this.user = user;
        this.editForm.patchValue({
          userName: user.userName,
          userFirstName: user.userFirstName,
          userLastName: user.userLastName
        });
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  public updateUser(): void {
    if (this.editForm.valid) {
      const updatedUser: User = {
        ...this.user,
        userFirstName: this.editForm.value.userFirstName,
        userLastName: this.editForm.value.userLastName
      };

      this.staffService.updateUser(updatedUser).subscribe(
        () => {
          this.router.navigate(['/staff-handling']);
        },
        (error) => {
          console.error('Error updating user:', error);
        }
      );
    }
  }

  public cancelEdit(): void {
    this.router.navigate(['/staff-handling']);
  }
}
