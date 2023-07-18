import { Component } from '@angular/core';
import { StaffService } from '../_services/staff.service';
import { BugService } from '../_services/bug.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.scss']
})
export class StaffComponent {
  staffProfile!:any;
  BugProcessess!:any;
  constructor(
    private staffService : StaffService,
    private bugService : BugService,
    private userAuthService : UserAuthService
  ){  }

  ngOnInit(){
    this.getStaffProfile();
  }

  getStaffProfile(){
    let userName = this.userAuthService.getUserName();
    this.staffService.getStaffProfile(userName).subscribe(
      {
        next: (res) => {
          this.staffProfile = res;
          this.getBugProcesses();
        },
        error: (error) => {
          console.log("Error while fetching staff profile : " + error);
        }
      }
    )
  }

  getBugProcesses(){
    this.bugService.getBugsByStaff(this.staffProfile.staffProfileId).subscribe(
      {
        next : (res) => {
          this.BugProcessess=res;
        },
        error : (err) => {
          console.log("Failed to Load Bugs"+err);
        }
      }
    )
  }
  
}
