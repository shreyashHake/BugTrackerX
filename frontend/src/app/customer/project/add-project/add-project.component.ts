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

  constructor(
    private router: Router,
    private projectService: ProjectService,
    private customerService : CustomerService
  ) {
    this.initializeForm();
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
  }


  addProject() {
    console.log("ID2 : "+ this.customerService.getProfileId())
    const customerProject : CustomerProject = {
      ...this.creatProject.value ,
      customerProfile : {
        customerProfileId : this.customerService.getProfileId()
      }
    }
    console.log(customerProject);
    this.projectService.saveProject(customerProject).subscribe({
      next: (response) => {
        console.log("it worked" + this.creatProject.value);

        this.creatProject.reset();
        this.router.navigate(['/customer'])
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  public backToProject() {

    this.router.navigate((["/customer"]))
  }





}
