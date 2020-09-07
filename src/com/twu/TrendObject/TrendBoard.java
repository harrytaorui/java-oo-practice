package com.twu.TrendObject;

import com.twu.UserObject.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrendBoard {

    private List<Trend> board;
    private User currentUser;

    public TrendBoard() {
        this.board = new ArrayList<>();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean addTrend(String trendName, boolean isSTrend){
        Optional<Trend> optionalTrend = findTrendByName(trendName);
        if (!optionalTrend.isPresent()) {
            Trend newTrend = new Trend(trendName, isSTrend);
            newTrend.setRank(board.size()+1);
            board.add(newTrend);
            System.out.println("热搜添加成功！");
            return true;
        } else {
            System.out.println("热搜已存在！");
            return false;
        }

    }

    public boolean buyTrend(String trendName, int rank, int bidPrice) {
        //return false if parameters are illegal
        if ( trendName == null || bidPrice <= 0 || rank <= 0 ||rank > board.size()) {
            System.out.println("输入不合法！");
            return false;
        }
        //return false if trend does not exist
        Optional<Trend> optionalTrend = findTrendByName(trendName);
        if (!optionalTrend.isPresent()) {
            System.out.println("该热搜不存在！");
            return false;
        }
        Trend newMoney = optionalTrend.get();
        Trend oldMoney = board.get(rank -1);
        //return false if new bid price <= old bid price
        if (oldMoney.getBidPrice() >= bidPrice) {
            System.out.println("购买价格低于"+oldMoney.getName()+"的价格！");
            return false;
        } else if (oldMoney.getBidPrice() > 0){
            //remove old trend if it is paid with lower bid price
            board.remove(rank-1);
        }
        newMoney.setRank(rank);
        newMoney.setBidPrice(bidPrice);
        System.out.println("购买成功！");
        return true;
    }

    public Optional<Trend> findTrendByName(String trendName) {
        return board.stream().filter(e -> e.getName().equals(trendName)).findFirst();
    }

    public void updateBoard() {
        //resort trends in descending votes order
        board.sort(Comparator.comparingInt(Trend::getVote).reversed());
        //create clean new board
        List<Trend> sorted = new ArrayList<>();
        IntStream.range(0,board.size()).forEach(i->sorted.add(null));
        //classify trends whether it is paid
        List<Trend> freeTrendList = board.stream().filter(e->e.getBidPrice()==0)
                .collect(Collectors.toList());
        List<Trend> paidTrendList = board.stream().filter(e->e.getBidPrice()>0)
                .collect(Collectors.toList());
        //put paid trends in new board based on their ranks
        paidTrendList.forEach(e->sorted.set(e.getRank()-1,e));
        //put free trends in place not occupied by paid trends
        for (int i = 0, j = 0;i < sorted.size();i++) {
            if (sorted.get(i) == null) {
                sorted.set(i, freeTrendList.get(j++));
            }
            sorted.get(i).setRank(i + 1);
        }
//      another way with O(n^2) time complexity
//      freeTrendList.forEach(e->sorted.set(sorted.indexOf(null),e));
//      sorted.forEach(e->e.setRank(sorted.indexOf(e)+1));
        board = sorted;
    }

    public void printBoard() {
        updateBoard();
        board.stream().forEach(trend -> {
            System.out.print(trend.getRank() + " ");
            System.out.print(trend.getName()+" ");
            System.out.println(trend.getVote());
        });
    }

    public void addVotes(String trendName, int votes) {
        if (currentUser.getVotes() >= votes){
            Optional<Trend> optionalTrend = findTrendByName(trendName);
            if (!optionalTrend.isPresent()) {
                System.out.println("热搜不存在！");
                return;
            }
            optionalTrend.get().addVote(votes);
            currentUser.reduceVotes(votes);
            System.out.println("投票成功！");
        }else {
            System.out.println("你的票数不足！剩余票数为："+
                    this.getCurrentUser().getVotes());
        }
    }

}