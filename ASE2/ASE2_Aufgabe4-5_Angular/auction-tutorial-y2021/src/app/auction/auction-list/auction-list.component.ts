import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { Auction } from '../auction';
import { AuctionDataService } from '../auction-data.service';
import { Observable } from 'rxjs';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-auction-list',
  templateUrl: './auction-list.component.html',
  styleUrls: ['./auction-list.component.scss'],
})
export class AuctionListComponent implements OnInit, OnDestroy {
  @Input() headerTitle!: string;
  @Output() titleClicked = new EventEmitter<string>();

  auctions!: Auction[];
  auctionsObservable!: Observable<Auction[]>;
  auctionSubscription!: Subscription;

  constructor(private auctionDataService: AuctionDataService) {
    // this.auctions = auctionDataService.getAuctions();
  }

  ngOnInit(): void {
    this.auctionsObservable = this.auctionDataService.getObservableAuctions();
    this.auctionSubscription = this.auctionsObservable.subscribe(
      (data) => (this.auctions = data)
    );
  }

  onTitleClicked(event: MouseEvent): void {
    this.titleClicked.emit('Title clicked' + event.type);
  }

  ngOnDestroy(): void {
    this.auctionSubscription.unsubscribe();
  }
}
