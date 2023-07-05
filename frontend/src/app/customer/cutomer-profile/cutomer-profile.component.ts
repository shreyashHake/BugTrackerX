import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-cutomer-profile',
  templateUrl: './cutomer-profile.component.html',
  styleUrls: ['./cutomer-profile.component.scss']
})
export class CutomerProfileComponent {
  customerProfile!: FormGroup;

  constructor(
    private userService: UserService,
    private router: Router
  ) {  }

  initializeForm() {
    this.customerProfile = new FormGroup({
      customerName: new FormControl('', Validators.required),
      customerCompany: new FormControl('', Validators.required),
      customerPhone: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  register() {
    console.log(this.customerProfile.value);
    this.router.navigate(['/login']);


  //   this.userService.registerCustomer(this.customerProfile.value).subscribe({
  //     next: (response) => {
  //       console.log(this.customerProfile.value);

  //       this.customerProfile.reset();
  //       this.router.navigate(['/login']);
  //     },
  //     error: (err) => {
  //       console.log(err);
  //       window.alert("User name already exists");
  //     }
  //   })
  }
}
