package com.twu;
import com.twu.TrendObject.TrendBoard;
import com.twu.UserObject.AdminUser;
import com.twu.UserObject.CommonUser;
import com.twu.UserObject.User;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private HashMap<String, User> userMap = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    private TrendBoard board = new TrendBoard();

    public void addUser(User user){
        String userName = user.getUserName();
        this.userMap.put(userName,user);
    }

    public void login() {
        System.out.println("请选择登陆的账户:\n1.管理员\n2.用户\n3.退出");
        int selection = sc.nextInt();
        sc.nextLine();
        if (selection == 1 || selection == 2) {
            System.out.println("请输入你的账号名:");
            String userName = sc.nextLine();
            System.out.println("请输入你的密码:");
            String password = sc.nextLine();
            if (!userMap.containsKey(userName)) {
                System.out.println("账号不存在，请重新输入");
                login();
            }
            User user = userMap.get(userName);
            if (!user.getPassword().equals(password)) {
                System.out.println("密码错误，请重新输入");
                login();
            }
            board.setCurrentUser(user);
            showBoard();
        } else if (selection == 3) {
            System.out.println("谢谢使用，再见！");
            System.exit(1);
        } else {
            System.out.println("输入有误，请重新输入:");
            login();
        }
    }

    public void showBoard() {
        User user = board.getCurrentUser();
        System.out.println("你好，"+user.getUserName()+":");
        if (user.getUserType() == 1) {
            System.out.println("1.查看热搜排行榜\n2.添加热搜\n3.添加超级热搜\n4.退出登陆");
            int selection = sc.nextInt();
            sc.nextLine();
            if (selection == 1) {
                board.printBoard();
            } else if (selection == 2 || selection == 3) {
                System.out.println("请输入热搜的名字:");
                String TrendName = sc.nextLine();
                boolean isSTrend = selection == 3;
                board.addTrend(TrendName, isSTrend);
            } else if (selection == 4) {
                board.setCurrentUser(null);
                login();
            } else {
                System.out.println("输入有误，请重新输入:");
            }
        } else if (user.getUserType() == 2) {
            System.out.println("1.查看热搜排行榜\n2.给热搜事件投票" +
                    "\n3.购买热搜\n4.添加热搜\n5.退出登陆");
            int selection = sc.nextInt();
            sc.nextLine();
            if (selection == 1) {
                board.printBoard();
            } else if (selection == 2) {
                System.out.println("请输入热搜的名字:");
                String TrendName = sc.nextLine();
                System.out.println("请输入你要投的票数:");
                int votes = sc.nextInt();
                board.addVotes(TrendName, votes);
            } else if (selection == 3) {
                System.out.println("请输入你要购买的热搜名字:");
                String TrendName = sc.nextLine();
                System.out.println("请输入你要购买的热搜排名:");
                int rank = sc.nextInt();
                System.out.println("请输入你要购买的热搜金额:");
                int bidPrice = sc.nextInt();
                board.buyTrend(TrendName,rank,bidPrice);
            } else if (selection == 4) {
                System.out.println("请输入要添加的热搜名字:");
                String TrendName = sc.nextLine();
                board.addTrend(TrendName,false);
            } else if (selection == 5) {
                board.setCurrentUser(null);
                login();
            } else {
                System.out.println("输入有误，请重新输入:");
            }
        }
        showBoard();
    }


    public static void main(String[] args) {
        Main main = new Main();
        User admin = new AdminUser("admin","admin");
        User user1 = new CommonUser("user1","user1");
        User user2 = new CommonUser("user2","user2");
        main.addUser(admin);
        main.addUser(user1);
        main.addUser(user2);
        main.login();
    }
}
