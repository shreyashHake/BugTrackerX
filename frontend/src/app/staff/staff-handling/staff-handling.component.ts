import { Component, OnInit } from '@angular/core';
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
    private staffService: StaffService) { }

  ngOnInit(): void {
    this.getStaffUsers();
  }

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

  hasUserRole(user: User, roleName: string): boolean {
    return Array.from(user.userRole).some((role: Role) => role.roleName === roleName);
  }

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
}
