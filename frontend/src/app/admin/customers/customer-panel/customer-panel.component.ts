import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from 'src/app/_services/customer.service';
import { ProjectService } from 'src/app/_services/project.service';

@Component({
  selector: 'app-customer-panel',
  templateUrl: './customer-panel.component.html',
  styleUrls: ['./customer-panel.component.scss']
})
export class CustomerPanelComponent {

  customerProfileId : any;
  customerProfile : any;
  customerProjects! : any[];
  constructor(
    private route: ActivatedRoute,
    private customerService : CustomerService,
    private projectService : ProjectService,
    private router : Router
    ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.customerProfileId = params['customer_id']; // Get the customer parameter value
      // Use the customerId to fetch the customer object from your data source or perform any necessary actions
      console.log("panel : "+ this.customerProfileId);
    });
    this.getCustomerProfile();
    this.getCustomerProjects();
  }


  getCustomerProfile(){
    console.log("Out " +this.customerProfileId)
    this.customerService.getCustomerProfileById(this.customerProfileId).subscribe(
      {
        next : (res) => {
          this.customerProfile = res;
          console.log("Customer_Profile : "+ this.customerProfile);
        },
        error : (error) =>{
          console.log("Error while fetching customer profile : "+error);
        }  
      }
    )
  }

  getCustomerProjects(){
      this.projectService.getAllProject(this.customerProfileId).subscribe(
        {
          next : (res) => {
            console.log(res);
            this.customerProjects = res;
          },
          error : (error) => {
            console.log("Failed to get customer projects : "+error);
          }
        }
      )

  }
  
}
