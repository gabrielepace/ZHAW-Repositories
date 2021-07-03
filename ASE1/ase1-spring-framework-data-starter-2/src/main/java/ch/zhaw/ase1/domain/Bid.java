package ch.zhaw.ase1.domain;


public class Bid {
    private int Amount;
    private String CancelExplanation;
    private String PlacedAtDateTime;

    public Bid(int amount, String cancelExplanation, String placedAtDateTime) {
        Amount = amount;
        CancelExplanation = cancelExplanation;
        PlacedAtDateTime = placedAtDateTime;
    }

    public Bid() {
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getCancelExplanation() {
        return CancelExplanation;
    }

    public void setCancelExplanation(String cancelExplanation) {
        CancelExplanation = cancelExplanation;
    }

    public String  getPlacedAtDateTime() {
        return PlacedAtDateTime;
    }

    public void setPlacedAtDateTime(String placedAtDateTime) {
        PlacedAtDateTime = placedAtDateTime;
    }
}
