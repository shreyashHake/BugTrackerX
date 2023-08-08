import { Component, Input } from '@angular/core';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-project-block',
  templateUrl: './project-block.component.html',
  styleUrls: ['./project-block.component.scss']
})
export class ProjectBlockComponent {
  @Input() projects:any;
  @Input() customerProfile:any;

  constructor(
    private userService : UserService
  ){}

  ngOnInit(){
  
  }

  isCustomer(){
    return this.userService.getIsCustomer();
  }
}
