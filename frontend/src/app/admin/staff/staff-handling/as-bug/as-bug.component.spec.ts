import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ASBugComponent } from './as-bug.component';

describe('ASBugComponent', () => {
  let component: ASBugComponent;
  let fixture: ComponentFixture<ASBugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ASBugComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ASBugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
