import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ABugComponent } from './a-bug.component';

describe('ABugComponent', () => {
  let component: ABugComponent;
  let fixture: ComponentFixture<ABugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ABugComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ABugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
