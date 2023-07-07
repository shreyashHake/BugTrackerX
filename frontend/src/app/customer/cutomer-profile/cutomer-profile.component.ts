import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router,ActivatedRoute } from '@angular/router';
import { CustomerProfile } from 'src/app/_model/customerProfile.model';
import { CustomerService } from 'src/app/_services/customer.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-cutomer-profile',
  templateUrl: './cutomer-profile.component.html',
  styleUrls: ['./cutomer-profile.component.scss']
})
export class CutomerProfileComponent {
  customerProfile!: FormGroup;
  userName! : string;
  
  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private customerService : CustomerService
  ) {  }

  initializeForm() {
    this.customerProfile = new FormGroup({
      customerName: new FormControl('', Validators.required),
      customerCompany: new FormControl('', Validators.required),
      customerPhone: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
     this.userName = params['userName'];
      // Use the userName parameter as needed
      console.log(this.userName);
    });
    this.initializeForm();
  }

  register() {

   const profile : CustomerProfile = {
      ...this.customerProfile.value,
      isActive : true,
      user :{
        userName : this.userName
      }
    }
    console.log("profile : ",profile);


    this.customerService.completeProfile(profile).subscribe({
      next: (response) => {
        console.log(this.customerProfile.value);

        this.customerProfile.reset();
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.log(err);
        window.alert("User name already exists");
      }
    })
  }
}
