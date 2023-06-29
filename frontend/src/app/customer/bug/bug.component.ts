import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-bug',
  templateUrl: './bug.component.html',
  styleUrls: ['./bug.component.scss']
})
export class BugComponent {
  // showModal = false;
  // selectedProject: any;

  // openModal(project: any) {
  //   this.selectedProject = project;
  //   this.showModal = true;
  // }

  // closeModal() {
  //   this.showModal = false;
  // }

  @Input() showModal = false;
  @Output() closeModalEvent = new EventEmitter<void>();

  closeModal() {
    this.showModal = false;
    this.closeModalEvent.emit();

  }
}
