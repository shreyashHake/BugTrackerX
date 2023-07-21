import { Component } from '@angular/core';
import { CustomerService } from '../_services/customer.service';
import { Project } from '../_model/project.model';
import { ProjectService } from '../_services/project.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent {
  customerProfile: any;

  projects: Project[] = [];
  profileId!: number;

  constructor(
    private customerService: CustomerService,
    private projectService: ProjectService,
    private userAuthService : UserAuthService

  ) {

  }

  ngOnInit() {
    this.getCustomerProfile();

  }

  getCustomerProfile() {
    const userName = this.userAuthService.getUserName();
    this.customerService.getCustomerProfile(userName).subscribe(
      {
        next: (res) => {
          this.customerProfile = res;
          this.getAllProject();
          this.customerService.setProfileId(this.customerProfile.customerProfileId);

          console.log("newly seted id : " + this.customerService.getProfileId());

        },
        error: (error) => {
          console.log("Error while fetching customer profile : " + error);
        }
      }
    )
  }

    getAllProject() {
      console.log( "Customer id : " + this.customerProfile.customerProfileId);

      this.projectService.getAllProject(this.customerProfile.customerProfileId).subscribe({
        next: (response) => {
          this.projects = response;
        },
        error: (err) => {
          console.log(err);
        }
      })
    }

}
