package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int hand1 = Utility.getHandRanking(p1Hand);
        int hand2 = Utility.getHandRanking(p2Hand);
        //higher hand wins
        if (hand1 > hand2) {
            return "Player 1 wins!";
        }
        else if (hand2 > hand1) {
            return "Player 2 wins!";
        }
        else {
            //if same hand, higher high card wins
            if (Utility.getRankValue(p1.getHighCard()) > Utility.getRankValue(p2.getHighCard())) {
                return "Player 1 wins!";
            }
            else if (Utility.getRankValue(p2.getHighCard()) > Utility.getRankValue(p1.getHighCard())) {
                return "Player 2 wins!";
            }
            else {return "Tie!";}
        }
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}