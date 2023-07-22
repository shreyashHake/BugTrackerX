import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BugService } from 'src/app/_services/bug.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';

@Component({
  selector: 'app-as-bug',
  templateUrl: './as-bug.component.html',
  styleUrls: ['./as-bug.component.scss']
})
export class ASBugComponent {
  staff_id!: any;
  bugId!: any;
  BugDetails!: any;
  commentText!: any;
  constructor(
    private route: ActivatedRoute,
    private bugService: BugService,
    private userAuthService: UserAuthService
  ) {
   
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.staff_id = params['staff_id'];
      this.bugId = params['bug_id'];
    });
    this.getBugDetails();
  }
  
  getBugDetails() {
    this.bugService.getBugDetails(this.bugId).subscribe(
      {
        next: (res) => {
          this.BugDetails = res;
          console.log("Bug Details : " + this.BugDetails);
        },
        error: (error) => {
          console.log("Failed to load bug details : " + error);
        }
      }
    )
  }
}
