import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerProject } from 'src/app/_model/customerProject.model';
import { CustomerService } from 'src/app/_services/customer.service';
import { ProjectService } from 'src/app/_services/project.service';
import { StaffService } from 'src/app/_services/staff.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.scss']
})
export class AddProjectComponent {
  creatProject!: FormGroup;
  profileId!: number;
  constructor(
    private router: Router,
    private projectService: ProjectService,
    private customerService : CustomerService
  ) {
    this.initializeForm();
    // this.getProfile();
   }

  initializeForm() {
    this.creatProject = new FormGroup({
      projectName: new FormControl('', Validators.required),
      projectDomain: new FormControl('', Validators.required),
      projectDesc: new FormControl('', [Validators.required]),
      projectStatus: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.initializeForm();
    this.getProfile();
  }

  // getProfile() {
  //   console.log("m1")
  //   this.customerService.getCustomerProfile().subscribe({
  //     next: (response) => {
  //       console.log("m2")

  //       this.profileId = response.customerProfileId;
  //       console.log("ID1 : "+ this.profileId)

  //     },
  //     error: (err) => {
  //           console.log("m3errror")

  //       console.log(err);
  //     }
  //   })
  // }

  getProfile() {
    this.customerService.getCustomerProfile().subscribe({
      next: (response) => {
        console.log("m2" + response);
  
        this.profileId = response.customerProfileId;
        console.log("ID1: " + this.profileId);
        
        // Handle the eagerly fetched userRole collection
        const userRoles = response.user.userRole;
        // Use the userRoles as needed
        
      },
      error: (err) => {
        console.log("m3error");
  
        console.log(err);
      }
    });
  }
  

  addProject() {
    this.getProfile();
    console.log("ID2 : "+ this.profileId)
    const customerProject : CustomerProject = {
      ...this.creatProject.value ,
      customerProfile : {
        customerProfileId : this.profileId
      }
    }
    console.log(customerProject);
    this.projectService.saveProject(customerProject).subscribe({
      next: (response) => {
        console.log("it worked" + this.creatProject.value);

        this.creatProject.reset();
        this.router.navigate(['/project'])
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  public backToProject() {
    this.router.navigate((["/project"]))
  }



}
