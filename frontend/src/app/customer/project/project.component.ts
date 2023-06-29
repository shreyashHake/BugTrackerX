import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Project } from 'src/app/_model/project.model';
import { User } from 'src/app/_model/user.model';
import { ProjectService } from 'src/app/_services/project.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss'],
})
export class ProjectComponent {
  projectRec: Project[] = [];
  showModal = false;

  constructor(private router: Router, private projectService: ProjectService) {}

  ngOnInit(): void {
    this.getStaffUsers();
  }

  getStaffUsers(): void {
    this.projectService.getAllProject().subscribe({
      next: (projects: Project[]) => {
        this.projectRec = projects;

        console.log(this.projectRec);
      },

      error: (err) => {
        console.log(err);
      },
    });
  }

  onModalClosed() {
    this.showModal = false;
  }
}
