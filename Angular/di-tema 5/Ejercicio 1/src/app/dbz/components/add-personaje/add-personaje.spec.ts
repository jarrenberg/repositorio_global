import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPersonaje } from './add-personaje';

describe('AddPersonaje', () => {
  let component: AddPersonaje;
  let fixture: ComponentFixture<AddPersonaje>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPersonaje]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPersonaje);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
