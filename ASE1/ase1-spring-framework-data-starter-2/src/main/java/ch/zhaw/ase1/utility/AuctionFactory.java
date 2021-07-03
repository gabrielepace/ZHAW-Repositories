package ch.zhaw.ase1.utility;


import ch.zhaw.ase1.domain.Auction;
import ch.zhaw.ase1.domain.AuctionItem;
import ch.zhaw.ase1.domain.Bid;
import ch.zhaw.ase1.domain.Category;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AuctionFactory {


    public Auction getAuction(boolean isTestAuction) {
        if (isTestAuction) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 7);
            Date endDate = new Date();
            endDate = calendar.getTime();
            AuctionItem auctionItem = new AuctionItem("This is a test item", "Test Image", "Test Titel");
            Category category = new Category("Test Category Description", "Test Category Name");
            ArrayList<Bid> bids = new ArrayList<Bid>();
            Bid bid = new Bid(15, "", "Today");
            bids.add(bid);
//            return new Auction("Ending Date", 100, 2, "Some other date", 10, true, category, auctionItem, bid);

        }
        return null;
    }
}

