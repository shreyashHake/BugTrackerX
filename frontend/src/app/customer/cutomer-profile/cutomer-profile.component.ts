import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer_Profile } from 'src/app/_model/customer.model';
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
    private router: Router,
    private route: ActivatedRoute
  ) { }

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

  completeProfile() {

    const userName = this.route.snapshot.paramMap.get('userName');
    const profile: Customer_Profile = {
      ...this.customerProfile.value,
      isActive: true,
      userName: userName
    };


  }
}
