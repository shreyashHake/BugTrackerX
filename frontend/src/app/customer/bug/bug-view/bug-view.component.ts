import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BugService } from 'src/app/_services/bug.service';

@Component({
  selector: 'app-bug-view',
  templateUrl: './bug-view.component.html',
  styleUrls: ['./bug-view.component.scss']
})
export class BugViewComponent {
  customerProfileId: any;
  projectId!: any;
  bugId!: any;
  BugDetails!:any;
  constructor(
    private route: ActivatedRoute,
    private bugService: BugService
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.customerProfileId = params['customer_id'];
      this.projectId = params['project_id'];
      this.bugId=params['bug_id'];
      console.log("panel c : " + this.customerProfileId);
      console.log("panel p: " + this.projectId);
    });  
    this.getBugDetails();
  }

  getBugDetails(){
    this.bugService.getBugDetails(this.bugId).subscribe(
      {
        next : (res) => {
          this.BugDetails = res;
          console.log("Bug Details : "+this.BugDetails);
        },
        error : (error) => {
          console.log("Failed to load bug details : "+error);
        }
      }
    )
  }
}
