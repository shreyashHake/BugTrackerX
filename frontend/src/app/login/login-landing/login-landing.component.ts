import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../_services/user.service';

@Component({
  selector: 'app-login-landing',
  templateUrl: './login-landing.component.html',
  styleUrls: ['./login-landing.component.scss']
})
export class LoginLandingComponent {
  constructor(
    private router: Router,
    private userService: UserService) {}


  public handleButtonClick(cardNumber: any) {
    if (cardNumber === 3) {
      this.userService.setIsCustomer(true);
    } else {
      this.userService.setIsCustomer(false);
    }
    this.router.navigate(['/login']);
  }
}
