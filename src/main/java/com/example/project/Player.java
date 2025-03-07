package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        allCards.clear();
        for (int i = 0; i < communityCards.size(); i++) {
            allCards.add(communityCards.get(i));
        }
        //add the player's hand to be included in all cards
        for (int i = 0; i < hand.size(); i++) {
            allCards.add(hand.get(i));
        }
        if (RoyalFlush()) {
            return "Royal Flush";
        }
        else if (StraightFlush()) {
            return "Straight Flush";
        }
        else if (FourOfAKind()) {
            return "Four of a Kind";
        }
        else if (FullHouse()) {
            return "Full House";
        }
        else if (Flush()) {
            return "Flush";
        }
        else if (Straight()) {
            return "Straight";
        }
        else if (ThreeOfAKind()) {
            return "Three of a Kind";
        }
        else if (TwoPair()) {
            return "Two Pair";
        }
        else if (OnePair()) {
            return "A Pair";
        }
        else if (HighCard()) {
            return "High Card";
        }
        else {return "Nothing";}

    }

    public void sortAllCards(){
        //insertion sort
        for (int i = 0; i < allCards.size(); i++) {
            Card val = allCards.get(i);
            int idx = i;
            while (idx > 0 && Utility.getRankValue(allCards.get(idx-1).getRank()) > (Utility.getRankValue(val.getRank()))) {
                idx--;
            }
            Card temp = allCards.get(idx);
            allCards.set(idx, val);
            allCards.set(i, temp);
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freq = new ArrayList<>();
        sortAllCards();
        //make sure list isnt null
        for (int i = 0; i < 13; i++) {
            freq.add(0);
        }
        //since the cards start counting at 2 i take their rank value and subtract 2 to get the index
        for (int i = 0; i < allCards.size(); i++) {
            int idx = Utility.getRankValue(allCards.get(i).getRank())-2;
            freq.set(idx, freq.get(idx) + 1);
        }
        return freq; 
    }

    public ArrayList<Integer> findSuitFrequency(){ 
        ArrayList<Integer> freq = new ArrayList<>();
        sortAllCards();
        //make sure list isnt null
        for (int i = 0; i < 4; i++) {
            freq.add(0);
        }
        for (int i = 0; i < allCards.size();i++) {
            int idx = Utility.getSuitPos(allCards.get(i).getSuit());
            freq.set(idx, freq.get(idx)+1);
        }
        return freq;
    }
    
    public boolean RoyalFlush() {
        sortAllCards();
        //royal flush is just a straight flush whose lowest value is 10
        return allCards.get(0).getRank() == "10" && StraightFlush();
    }

    public boolean StraightFlush() {
        return Straight() && Flush();
    }

    public boolean FourOfAKind() {
        //check for a number with 4 instances
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 4) {
                return true;
            }
        }
        return false;
    }

    //full house is 3 of a kind + a pair of cards
    public boolean FullHouse() {
        return ThreeOfAKind() && OnePair();
    }

    public boolean Flush() {
        for (int i = 0; i < findSuitFrequency().size(); i++) {
            if (findSuitFrequency().get(i) == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean Straight() {
        sortAllCards();
        int idx = findRankingFrequency().indexOf(1);
        //checks for out of bounds or non-single rank counts
        if (idx > findRankingFrequency().size()-5 || idx == -1) {
            return false;
        }
        //checks for five 1's back to back
        for (int i = 0; i < 5; i++) {
            if (findRankingFrequency().get(idx+i) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean ThreeOfAKind() {
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean TwoPair() {
        int count = 0;
        //counts the amount of pairs, if there are 2, return true
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 2) {
                count++;
            }
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean OnePair() {
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean HighCard() {
        String rank = "";
        //use the ranking frequency list to check for the highest value then set rank to that
        for (int i = findRankingFrequency().size()-1; i >= 0; i--) {
            if (findRankingFrequency().get(i) == 1) {
                rank = ranks[i];
                break;
            }
        }
        //check if the highest value of all cards is in player's hand
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRank() == rank) {
                return true;
            }
        }
        return false;
    }

    //compares the cards in hand in case of a tie to use the high card
    public String getHighCard() {
        if (Utility.getRankValue(hand.get(0).getRank()) > Utility.getRankValue(hand.get(1).getRank())) {
            return hand.get(0).getRank();
        }
        else {return hand.get(1).getRank();}
    }

    @Override
    public String toString(){
        return hand.toString();
    }




}
