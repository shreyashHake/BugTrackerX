import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { StaffProfile } from 'src/app/_model/staffProfile.model';
import { BugService } from 'src/app/_services/bug.service';
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
  @Input() bugId !: number;
  @Output() closeModalEvent = new EventEmitter<void>();


  constructor(
    private staffService: StaffService,
    private bugService : BugService
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

    this.bugService.assignStaffToBug(this.staffProfileId,this.bugId).subscribe(
      {
        next : (res) => {
          alert(res);
        },
        error : (error) => {
          alert("Assign Staff To Bug Successfully");
        }
      }
    )
    
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
