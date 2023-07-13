import { Component } from '@angular/core';
import { CustomerService } from '../_services/customer.service';
import { Project } from '../_model/project.model';
import { ProjectService } from '../_services/project.service';

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
    private projectService: ProjectService
  ) {

  }

  ngOnInit() {
    this.getCustomerProfile();

  }

  getCustomerProfile() {
    this.customerService.getCustomerProfile().subscribe(
      {
        next: (res) => {
          this.customerProfile = res;
          console.log("Working nicely here ; " + res.customerName);
          console.log("Customer_Profile : " + this.customerProfile);
          this.getAllProject();
        },
        error: (error) => {
          console.log("Error while fetching customer profile : " + error);
        }
      }
    )
  }

    getAllProject() {
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
