import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BugService } from 'src/app/_services/bug.service';
import { ProjectService } from 'src/app/_services/project.service';

@Component({
  selector: 'app-project-view',
  templateUrl: './project-view.component.html',
  styleUrls: ['./project-view.component.scss']
})
export class ProjectViewComponent {
  customerProjects!: any[];
  customerProfileId: any;
  projectId!: any;
  project!: any;
  Bugs!: any;
  showModal = false;


  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService,
    private bugService: BugService
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.customerProfileId = params['customer_id'];
      this.projectId = params['project_id'];
      console.log("panel c : " + this.customerProfileId);
      console.log("panel p: " + this.projectId);
    });
    this.getCustomerProjects();
  }




  getCustomerProjects() {
    this.projectService.getAllProject(this.customerProfileId).subscribe(
      {
        next: (res) => {

          this.customerProjects = res;
          console.log("customer Projects : "+this.customerProjects[0].projectDomain);
          this.getProject();
        },
        error: (error) => {
          console.log("Failed to get customer projects : " + error);
        }
      }
    )

  }

  getProject() {
    console.log("customer Projects 1 : "+this.customerProjects[0].projectDomain);
    for (let p of this.customerProjects) {
      if (p.projectId == this.projectId) {
        this.project = p;
        this.getBugs();
        break;
      }
    }
    console.log("current project :: "+this.project);
  }

  getBugs() {
    this.bugService.getBugs(this.project.projectId).subscribe(
      {
        next: (res) => {
          this.Bugs = res;
          console.log("Bugs : " + this.Bugs);
        },
        error: (error) => {
          console.log("Failed to load bugs : " + error);
        }
      }
    )
  }

  onModalClosed() {
    this.showModal = false;
  }

  reGetBugs() {
    this.getBugs();
  }

  deleteProject(id : number) {
    console.log(id);

  }
}
