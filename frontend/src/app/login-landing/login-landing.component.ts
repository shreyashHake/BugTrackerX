import { Component } from '@angular/core';

@Component({
  selector: 'app-login-landing',
  templateUrl: './login-landing.component.html',
  styleUrls: ['./login-landing.component.scss']
})
export class LoginLandingComponent {

  public handleButtonClick(cardNumber: any) {
    console.log('Clicked on card', cardNumber);
    // Add your desired functionality here
  }
}
