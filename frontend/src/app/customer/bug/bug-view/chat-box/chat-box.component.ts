import { DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { CustomerService } from 'src/app/_services/customer.service';
import { StaffService } from 'src/app/_services/staff.service';

@Component({
  selector: 'app-chat-box',
  templateUrl: './chat-box.component.html',
  styleUrls: ['./chat-box.component.scss']
})
export class ChatBoxComponent {
  @Input() thread: any;
  sanitizedHtml!: SafeHtml;
  time!:any;
  Profile!:any;
  constructor(
    private sanitizer: DomSanitizer,
    private datePipe: DatePipe,
    private customerService: CustomerService,
    private staffService : StaffService
  ) {
  }

  ngOnInit() {
    this.sanitizedHtml = this.sanitizer.bypassSecurityTrustHtml(this.thread.comment);
    this.time = this.convertToRelativeTime(this.thread.commentDateTime);
    this.getProfile();
  }


  convertToRelativeTime(dateTime: string): string {
    const parsedDateTime = new Date(dateTime);
    const now = new Date();
    const timeDifference = Math.abs(now.getTime() - parsedDateTime.getTime());

    if (timeDifference < 3600000) {
      const minutesDifference = Math.ceil(timeDifference / (1000 * 60));
      return `${minutesDifference} minutes ago`;
    } else if (timeDifference < 86400000) {
      const hoursDifference = Math.ceil(timeDifference / (1000 * 60 * 60));
      return `${hoursDifference} hours ago`;
    } else if (timeDifference < 604800000) {
      const daysDifference = Math.ceil(timeDifference / (1000 * 60 * 60 * 24));
      return `${daysDifference} days ago`;
    } else if (timeDifference < 2628000000) {
      const weeksDifference = Math.ceil(timeDifference / (1000 * 60 * 60 * 24 * 7));
      return `${weeksDifference} weeks ago`;
    } else {
      const formattedDateTime = this.datePipe.transform(parsedDateTime, 'mediumDate');
      return `On ${formattedDateTime}`;
    }
  }

  getProfile(){
    if(this.thread.commentBy=='staff'){
      this.getStaffProfile();
    }else{
      this.getCustomerProfile();
    }
  }

  getCustomerProfile() {
    this.customerService.getCustomerProfile().subscribe(
      {
        next: (res) => {
          this.Profile = res;
        },
        error: (error) => {
          console.log("Error while fetching customer profile : " + error);
        }
      }
    )
  }

  getStaffProfile(){
    this.staffService.getStaffProfile(this.thread.user.userName).subscribe(
      {
        next: (res) => {
          this.Profile = res;
        },
        error: (error) => {
          console.log("Error while fetching staff profile : " + error);
        }
      }
    )
  }
}
