import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerFamilyComponent } from './customer-family.component';

describe('CustomerFamilyComponent', () => {
  let component: CustomerFamilyComponent;
  let fixture: ComponentFixture<CustomerFamilyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerFamilyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerFamilyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
