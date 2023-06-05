import { Component } from '@angular/core';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-staff-handling',
  templateUrl: './staff-handling.component.html',
  styleUrls: ['./staff-handling.component.scss']
})
export class StaffHandlingComponent {

  modalName: Modal | undefined;

  save() {
    this.modalName?.toggle();
  }

  openModal(element: HTMLElement) {
    this.modalName = new Modal(element, {});
    this.modalName?.show();
  }

}
