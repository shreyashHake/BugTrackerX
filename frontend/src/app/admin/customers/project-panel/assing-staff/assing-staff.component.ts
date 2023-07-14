import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { StaffProfile } from 'src/app/_model/staffProfile.model';
import { StaffService } from 'src/app/_services/staff.service';

@Component({
  selector: 'app-assing-staff',
  templateUrl: './assing-staff.component.html',
  styleUrls: ['./assing-staff.component.scss']
})
export class AssingStaffComponent {
  selectStaff!: FormGroup;
  staffs !: StaffProfile[];

  @Input() showModal = false;
  @Input() projectId !: number;
  @Output() closeModalEvent = new EventEmitter<void>();


  constructor(
    private staffService: StaffService
  ) {
    this.initializeForm();

   }

  initializeForm() {
    this.selectStaff = new FormGroup({
      staff: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.initializeForm();
    this.getAllStaff();
  }


  assignStaff() {

  }

  public getAllStaff() {

    this.staffService.getAllStaff().subscribe({
      next: (res) => {
        this.staffs = res;
        console.log("Staff details " + this.staffs);

      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  closeModal() {
    this.showModal = false;
    this.closeModalEvent.emit();
  }
}
