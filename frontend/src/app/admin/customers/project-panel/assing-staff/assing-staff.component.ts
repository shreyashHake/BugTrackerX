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
  staffProfileId !: number;

  @Input() showModal = false;
  @Input() projectId !: number;
  @Output() closeModalEvent = new EventEmitter<void>();


  constructor(
    private staffService: StaffService
  ) {
    // this.initializeForm();
    // this.getAllStaff();
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
    console.log("Staff id is : " + this.selectStaff.value.staff);
    this.staffProfileId = +this.selectStaff.value.staff;

    console.log(typeof(this.selectStaff.value.staff));

    // converting to number to use in bug-process . . . .
    console.log(typeof(this.staffProfileId));
    this.closeModal();
  }

  public getAllStaff() {

    this.staffService.getAllStaff().subscribe({
      next: (res) => {
        this.staffs = res;
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
