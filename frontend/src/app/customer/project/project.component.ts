import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Project } from 'src/app/_model/project.model';
import { User } from 'src/app/_model/user.model';
import { CustomerService } from 'src/app/_services/customer.service';
import { ProjectService } from 'src/app/_services/project.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss'],
})
export class ProjectComponent {
  projects: Project[] = [];
  showModal = false;
  profileId!: number;
  projectId!:number;
  constructor(
    private router: Router,
     private projectService: ProjectService,
    private customerService: CustomerService,
    private userAuthService : UserAuthService
    ) { }

  ngOnInit(): void {
    this.getProfile();
  }

  getProfile() {
    const userName = this.userAuthService.getUserName();
    this.customerService.getCustomerProfile(userName).subscribe((response) => {
      this.profileId = response.customerProfileId;
      this.customerService.setProfileId(this.profileId);
      console.log("ID1: " + this.profileId);
      this.getAllProject();
    }, (error) => {
      console.log(error);
    });

  }

  onModalClosed() {
    this.showModal = false;
  }

  getAllProject() {
    this.projectService.getAllProject(this.profileId).subscribe({
      next: (response) => {
        this.projects = response;
      },

      error: (err) => {
        console.log(err);

      }
    })
  }
}
