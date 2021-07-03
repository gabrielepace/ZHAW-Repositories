package ch.zhaw.ase1.config;

import ch.zhaw.ase1.domain.Auction;
import ch.zhaw.ase1.domain.AuctionItem;
import ch.zhaw.ase1.domain.Bid;
import ch.zhaw.ase1.domain.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

//@ComponentScan(basePackages = "domain")
@Configuration
public class SpringConfiguration {

    @Bean(name = "auction")
    public Auction auction() {
        System.out.println("SpringConfig: Getting Auction");
        return new Auction();
    }

    @Bean(name = "bidArrayList")
    public ArrayList bidArray() {
        System.out.println("SpringConfig: Getting Bid Array List");
        Bid bid = new Bid();
        bid.setAmount(10);
        bid.setCancelExplanation("N/A");
        bid.setPlacedAtDateTime("Example");
        ArrayList<Bid> bidArrayList = new ArrayList<Bid>();
        bidArrayList.add(bid);
        return bidArrayList;
    }

    @Bean(name = "bid")
    public Bid bid() {
        System.out.println("SpringConfig: Getting Bid");
        return new Bid();
    }


    @Bean(name = "auctionItem")
    public AuctionItem auctionItem() {
        System.out.println("SpringConfig: Getting Aution Item");
        return new AuctionItem();
    }

    @Bean(name = "category")
    public Category category() {
        System.out.println("SpringConfig: Getting Category");
        return new Category();
    }


}
