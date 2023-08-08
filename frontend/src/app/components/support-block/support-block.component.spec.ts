import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupportBlockComponent } from './support-block.component';

describe('SupportBlockComponent', () => {
  let component: SupportBlockComponent;
  let fixture: ComponentFixture<SupportBlockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupportBlockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SupportBlockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
