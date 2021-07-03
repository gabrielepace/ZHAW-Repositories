package ch.zhaw.ase1;

import ch.zhaw.ase1.config.SpringConfiguration;
import ch.zhaw.ase1.domain.Auction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Auction auction = (Auction) context.getBean("auction");
        System.out.println(auction.getCategory().getName());
        System.out.println(auction.getBidArray().get(auction.getBidArray().size()-1).getPlacedAtDateTime());
    }
}
