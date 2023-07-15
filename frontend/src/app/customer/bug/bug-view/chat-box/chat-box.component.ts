import { DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { CustomerService } from 'src/app/_services/customer.service';

@Component({
  selector: 'app-chat-box',
  templateUrl: './chat-box.component.html',
  styleUrls: ['./chat-box.component.scss']
})
export class ChatBoxComponent {
  @Input() thread: any;
  sanitizedHtml!: SafeHtml;
  time!:any;
  customerProfile!:any;
  constructor(
    private sanitizer: DomSanitizer,
    private datePipe: DatePipe,
    private customerService: CustomerService
  ) {
  }

  ngOnInit() {
    this.sanitizedHtml = this.sanitizer.bypassSecurityTrustHtml(this.thread.comment);
    this.time = this.convertToRelativeTime(this.thread.commentDateTime);
    this.getCustomerProfile();
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

  
  getCustomerProfile() {
    this.customerService.getCustomerProfile().subscribe(
      {
        next: (res) => {
          this.customerProfile = res;
        },
        error: (error) => {
          console.log("Error while fetching customer profile : " + error);
        }
      }
    )
  }
}
