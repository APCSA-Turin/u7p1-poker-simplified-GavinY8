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
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){     
         
        return "Nothing";
    }

    public void sortAllCards(){
        for (int i = 0; i < allCards.size(); i++) {
            Card val = allCards.get(i);
            int idx = i;
            while (idx > 0 && allCards.get(idx-1).getRank().compareTo(val.getRank()) > 0) {
                idx--;
            }
            Card temp = allCards.get(idx);
            allCards.set(idx, val);
            allCards.set(idx+1, temp);
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freq = new ArrayList<>(13);
        for (int i = 0; i < allCards.size(); i++) {
            int idx = Utility.getRankValue(allCards.get(i).getRank())-2;
            freq.set(idx, freq.get(i) + 1);
        }
        return freq; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> freq = new ArrayList<>(4);
        ArrayList<String> suits = new ArrayList<>(Arrays.asList(Utility.getSuits()));
        for (int i = 0; i < allCards.size(); i++) {
            freq.set(suits.indexOf(allCards.get(i).getSuit()), freq.get(i) + 1);
        }
        return freq; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
