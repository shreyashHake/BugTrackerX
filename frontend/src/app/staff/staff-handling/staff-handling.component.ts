import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from 'src/app/_model/role.model';
import { User } from 'src/app/_model/user.model';
import { StaffService } from 'src/app/_services/staff.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-staff-handling',
  templateUrl: './staff-handling.component.html',
  styleUrls: ['./staff-handling.component.scss']
})
export class StaffHandlingComponent implements OnInit {
  staffUsers: User[] = [];

  constructor(
    private router : Router,
    private userService : UserService,
    private staffService: StaffService) { }

  ngOnInit(): void {
    this.getStaffUsers();
  }

  // getting user :
  getStaffUsers(): void {
    this.staffService.getAlluser().subscribe(
      (users: User[]) => {
        this.staffUsers = users;
      },
      (error) => {
        console.error('Error retrieving staff users:', error);
      }
    );
  }

  // checking if thre role matches to 'staff'
  hasUserRole(user: User, roleName: string): boolean {
    return Array.from(user.userRole).some((role: Role) => role.roleName === roleName);
  }

  // deleting staff
  deleteStaffUser(userName: string) {
    this.staffService.deleteStaffUser(userName).subscribe({
      next: (response) => {
        this.getStaffUsers();
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  // warning to delete staff
  public warnAdmin(staffUsername : string) {
    if(confirm("Are you sure to delete : '"+ staffUsername + "' ?")) {
      this.deleteStaffUser(staffUsername);
    }
  }

  // editing staff user
  public editStaffUser(userName: string) {
    this.router.navigate(['/edit-staff', userName]);
  }

}
