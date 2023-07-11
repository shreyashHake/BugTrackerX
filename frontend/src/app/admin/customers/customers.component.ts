import { Component } from '@angular/core';
import { CustomerService } from 'src/app/_services/customer.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent {

  customerProfiles : any;

  constructor(
    private customerService : CustomerService
  ){
  }

  ngOnInit(){
    console.log("Loading")
    this.getAllCustomerProfiles();
  }

  getAllCustomerProfiles(){
    this.customerService.getCustomerProfiles().subscribe(
      {
        next : (res) => {
          this.customerProfiles = res;
          console.log(this.customerProfiles);
        },
        error : (error) => {
          console.log("Failed TO Load Customers : "+error)
        }
      }
    )
  }
}
