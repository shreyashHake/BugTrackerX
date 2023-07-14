import { Component, Input } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-chat-box',
  templateUrl: './chat-box.component.html',
  styleUrls: ['./chat-box.component.scss']
})
export class ChatBoxComponent {
  @Input() thread:any;
  sanitizedHtml!: SafeHtml;
  constructor(private sanitizer: DomSanitizer,){
  }

  ngOnInit(){
    this.sanitizedHtml = this.sanitizer.bypassSecurityTrustHtml(this.thread);
  }
}
