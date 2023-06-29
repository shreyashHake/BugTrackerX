import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    private projectService: ProjectService
  ) {
    this.initializeForm();

   }

  initializeForm() {
    this.creatProject = new FormGroup({
      title: new FormControl('', Validators.required),
      description: new FormControl('', [Validators.required]),
      priority: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  addProject() {
    console.log(this.creatProject.value);
    this.projectService.saveProject(this.creatProject.value).subscribe({
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
