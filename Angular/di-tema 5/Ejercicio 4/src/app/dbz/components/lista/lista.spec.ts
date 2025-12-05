import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaDBZ } from './lista';

describe('Lista', () => {
  let component: ListaDBZ;
  let fixture: ComponentFixture<ListaDBZ>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaDBZ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaDBZ);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
