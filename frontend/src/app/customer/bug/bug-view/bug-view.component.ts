import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Comment } from 'src/app/_model/Comment.model';
import { BugService } from 'src/app/_services/bug.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';

@Component({
  selector: 'app-bug-view',
  templateUrl: './bug-view.component.html',
  styleUrls: ['./bug-view.component.scss']
})
export class BugViewComponent {
  showModal = false;
  changeBugStatus!: FormGroup;

  customerProfileId: any;
  projectId!: any;
  bugId!: any;
  BugDetails!: any;
  commentText!: any;


  initializeForm() {
    this.changeBugStatus = new FormGroup({
      bugStatus: new FormControl('', Validators.required)
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

  changeBugStatusMethod() {
    let status = this.changeBugStatus.get('bugStatus')?.value;
    // alert("Changing Status : " + status);
    this.bugService.changeBugStatus(this.bugId, status).subscribe(
      (res) => {
        alert(res);
      }
    );
    this.getBugDetails();
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
        commentBy: "customer",
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
