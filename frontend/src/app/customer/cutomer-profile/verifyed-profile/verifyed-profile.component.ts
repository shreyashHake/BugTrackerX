import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-verifyed-profile',
  templateUrl: './verifyed-profile.component.html',
  styleUrls: ['./verifyed-profile.component.scss']
})
export class VerifyedProfileComponent {
  isVerified!:any;

  constructor(
    private route : ActivatedRoute
  ){}

  ngOnInit(){
    this.route.params.subscribe(params => {
      this.isVerified = params['isVerified'];
     });
  }
}
