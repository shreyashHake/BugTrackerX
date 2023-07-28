import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyedProfileComponent } from './verifyed-profile.component';

describe('VerifyedProfileComponent', () => {
  let component: VerifyedProfileComponent;
  let fixture: ComponentFixture<VerifyedProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerifyedProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerifyedProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
