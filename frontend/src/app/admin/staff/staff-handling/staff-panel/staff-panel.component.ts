import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
  staff_id:any;
  constructor(
    private route: ActivatedRoute,
    private staffService : StaffService,
    private bugService : BugService,
    private userAuthService : UserAuthService
  ){  }

  ngOnInit(){
    this.route.params.subscribe(params => {
      this.staff_id = params['staff_id'];
    });
    this.getStaffProfile();
  } 

  getStaffProfile(){
    
    this.staffService.getStaffProfileById(this.staff_id).subscribe(
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
