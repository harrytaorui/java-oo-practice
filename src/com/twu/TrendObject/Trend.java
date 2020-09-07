package com.twu.TrendObject;

public class Trend {

    //trend rank;
    private int rank;
    //name of trend
    private String name;
    //number of votes
    private int vote;
    //whether the trend is super trend
    private boolean isSTrend = false;
    //bid price
    private int bidPrice;

    public Trend(String name) {
        this.name = name;
    }

    public Trend(String TrendName, boolean isSTrend) {
        this.name = TrendName;
        this.isSTrend = isSTrend;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVote() {
        return vote;
    }

    public void addVote(int vote) {
        if (isSTrend) {
            this.vote += (vote * 2);
        } else {
            this.vote += vote;
        }
    }

    public boolean isSTrend() {
        return isSTrend;
    }

    public void setSTrend(boolean STrend) {
        isSTrend = STrend;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }


}
