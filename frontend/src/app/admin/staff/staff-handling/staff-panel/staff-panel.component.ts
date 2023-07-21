import { Component } from '@angular/core';
import { BugService } from 'src/app/_services/bug.service';
import { StaffService } from 'src/app/_services/staff.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';


@Component({
  selector: 'app-staff-panel',
  templateUrl: './staff-panel.component.html',
  styleUrls: ['./staff-panel.component.scss']
})
export class StaffPanelComponent {
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
