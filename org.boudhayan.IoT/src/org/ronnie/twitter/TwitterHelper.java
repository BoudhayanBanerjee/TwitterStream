/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ronnie.twitter;


import twitter4j.auth.AccessToken;
import twitter4j.*;
import java.util.*;

/**
 *
 * @author Ronnie
 */
public class TwitterHelper {
    
    private static final String consumerKey = "xxxxx";
    private static final String consumerSecret = "xxxxx";
    private static final String accessToken = "xxxxx";
    private static final String accessSecret = "xxxxx";
    
    void getUserTimeline(String[] user){
        
        Twitter twitter = new TwitterFactory().getInstance();
        
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessSecret));
        try {
        	for(int i=0;i<user.length;i++){
                    ResponseList<Status> contactStatus = twitter.getUserTimeline(user[i],new Paging(1,20));

                    System.out.println(user[i].toUpperCase());
                    System.out.println("+-------------------------------+\n");
                    for(Status j: contactStatus) {
                            System.out.println(j.getText());
                            
                    }
                    System.out.println("+-------------------------------+\n");
                }

        }catch(Exception e ){
        	
            System.out.println("Exception found while fetching User Timeline :"+e);
        }
    }
    
        
    void twitterStream(double westLongitude,double southLatitude,double eastLongitude,double northLatitude){
        
        //create new twitter stream object
        TwitterStream twitter = new TwitterStreamFactory().getInstance(); 
        
        
        //authentication 
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessSecret));
        
        
        //create new listener object
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " \t:\t " + status.getText());
                System.out.println("+-----------------------------------------------------------------------------------------------------+");
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        twitter.addListener(listener);
       
        FilterQuery query = new FilterQuery();
        //String[] keywords = {"earthquake"};
               
        double[][] location = {{westLongitude,southLatitude},{eastLongitude,northLatitude}};
        query.language("en").locations(location);
        
        twitter.filter(query);
    }
    
    void twitterStream(String[] keyword){
        //create new twitter stream object
        TwitterStream twitter = new TwitterStreamFactory().getInstance(); 
        
        
        //authentication 
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessSecret));
        
        
        //create new listener object
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " \t:\t " + status.getText());
                System.out.println("+-----------------------------------------------------------------------------------------------------+");
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        twitter.addListener(listener);
       
        FilterQuery query = new FilterQuery();
        query.language("en").track(keyword);
        
        twitter.filter(query);
        
    }
   
    
}
