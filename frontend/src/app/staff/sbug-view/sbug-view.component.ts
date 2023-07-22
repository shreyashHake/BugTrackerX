import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Comment } from 'src/app/_model/Comment.model';
import { BugService } from 'src/app/_services/bug.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';

@Component({
  selector: 'app-sbug-view',
  templateUrl: './sbug-view.component.html',
  styleUrls: ['./sbug-view.component.scss']
})
export class SBugViewComponent {
  showModal = false;
  changeGlobalBugStatus!: FormGroup;

  customerProfileId: any;
  projectId!: any;
  bugId!: any;
  BugDetails!: any;
  commentText!: any;


  initializeForm() {
    this.changeGlobalBugStatus = new FormGroup({
      bugGlobalStatus: new FormControl('', Validators.required)
    });
  }

  constructor(
    private route: ActivatedRoute,
    private bugService: BugService,
    private userAuthService: UserAuthService
  ) {
    this.initializeForm();
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
    this.initializeForm();
  }

  closeModal() {
    this.showModal = false;
  }

  changeGlobalBugStatusMethod() {
    let status = this.changeGlobalBugStatus.get('bugGlobalStatus')?.value;
    this.bugService.changeGlobalBugStatus(this.BugDetails.bugProcessId, status).subscribe(
      (res) => {
        alert(res);
      },
      (err) => {
        
        this.getBugDetails();
      }
    );
   
    this.closeModal();
    
  }

  getComment(comment: any) {
    this.commentText = comment;
    if (this.commentText == "") {
      alert("Please Write Something ...");
    } else {
      let userName = this.userAuthService.getUserName();
      let comment: Comment = {
        commentNumber: 3,
        comment: this.commentText,
        commentBy: "staff",
        user: {
          userName: userName
        },
        bugProcess: {
          bugProcessId: this.BugDetails.bugProcessId
        }
      }
      this.bugService.addCommentToBug(comment).subscribe(
        {
          next: (res) => {
            // alert("Thread added");
            this.getBugDetails();
          },
          error: (error) => {
            alert("Failed To add Thread");
          }
        }
      )

    }

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
