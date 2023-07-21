import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Comment } from 'src/app/_model/Comment.model';
import { BugService } from 'src/app/_services/bug.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';

@Component({
  selector: 'app-a-bug',
  templateUrl: './a-bug.component.html',
  styleUrls: ['./a-bug.component.scss']
})
export class ABugComponent {
  customerProfileId: any;
  projectId!: any;
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
      this.customerProfileId = params['customer_id'];
      this.projectId = params['project_id'];
      this.bugId = params['bug_id'];
      console.log("panel c : " + this.customerProfileId);
      console.log("panel p: " + this.projectId);
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
