import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SBugViewComponent } from './sbug-view.component';

describe('SBugViewComponent', () => {
  let component: SBugViewComponent;
  let fixture: ComponentFixture<SBugViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SBugViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SBugViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
