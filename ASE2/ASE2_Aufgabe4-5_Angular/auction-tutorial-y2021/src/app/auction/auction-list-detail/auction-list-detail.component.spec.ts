import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuctionListDetailComponent } from './auction-list-detail.component';

describe('AuctionListDetailComponent', () => {
  let component: AuctionListDetailComponent;
  let fixture: ComponentFixture<AuctionListDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuctionListDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuctionListDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
