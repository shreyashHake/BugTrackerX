import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Bug } from 'src/app/_model/bug.model';
import { BugService } from 'src/app/_services/bug.service';

@Component({
  selector: 'app-add-bug',
  templateUrl: './add-bug.component.html',
  styleUrls: ['./add-bug.component.scss']
})
export class AddBugComponent {
  addBug!: FormGroup;

  @Input() showModal = false;
  @Input() projectId !: number;
  @Output() closeModalEvent = new EventEmitter<void>();


  constructor(
    private router: Router,
   private bugService : BugService
  ) {
    this.initializeForm();
   }

  initializeForm() {
    this.addBug = new FormGroup({
      bugTitle: new FormControl('', Validators.required),
      bugDesc: new FormControl('', Validators.required),
      bugPriority: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.initializeForm();
  }


  addBugMethod() {
    const bug : Bug = {
      ...this.addBug.value,
      bugStatus : "Not Resolved",
      customerProject : {
        projectId : this.projectId
      }
    }
    console.log(bug)
    this.bugService.addBug(bug).subscribe({
      next: (response) => {
        console.log("it worked" + response);
        this.addBug.reset();
        this.closeModal();
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
