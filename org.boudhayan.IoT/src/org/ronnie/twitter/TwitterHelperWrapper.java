/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ronnie.twitter;

import twitter4j.*;
import java.util.*;

/**
 *
 * @author Ronnie
 */
public class TwitterHelperWrapper {
    
     public static void main(String[] args) throws TwitterException {
        
        TwitterHelper tt = new TwitterHelper();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the method number :"
                          + "\n1.Get tweets from selected multiple/single user"
                          + "\n2.Get tweet stream based on particular keywords"
                          + "\n3.Get tweet stream based on particular geo coordinates\n");
        int selection = sc.nextInt();
        
        switch(selection){
            case 1:
                Scanner tu = new Scanner(System.in);
                System.out.println("Enter number of users :");
                int numUser = tu.nextInt();
                String[] user = new String[numUser];
                
                for(int i=1;i<=numUser;i++){
                    System.out.println("Enter user "+i+" :" );
                    Scanner key =new Scanner(System.in);
                    user[i-1]=key.nextLine();
                }
                tt.getUserTimeline(user);
                break;
            case 2:
                Scanner ts = new Scanner(System.in);
                System.out.println("Enter number of keywords :");
                int numKeyword = ts.nextInt();
                String[] keyword = new String[numKeyword];
                
                for(int i=1;i<=numKeyword;i++){
                    System.out.println("Enter keyword "+i+" :" );
                    Scanner key =new Scanner(System.in);
                    keyword[i-1]=key.nextLine();
                }
                tt.twitterStream(keyword);
                break;
            case 3:    
                Scanner loc =new Scanner(System.in);
                System.out.println("Enter west longitude :");
                double westLongitude = loc.nextDouble();
                System.out.println("Enter east longitude :");
                double eastLongitude = loc.nextDouble();
                System.out.println("Enter north latitude :");
                double northLatitude = loc.nextDouble();
                System.out.println("Enter south latitude :");
                double southLatitude = loc.nextDouble();
                 
                tt.twitterStream(westLongitude, southLatitude, eastLongitude, northLatitude);
                break;
            default:
                System.out.println("Not a valid option");
        }
       
    }
    
}
