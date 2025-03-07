package com.example.project;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        
        // Player 1 has a Straight Flush: 5, 6, 7, 8, 9 of Spades
        player1.addCard(new Card("5", "♠"));
        player1.addCard(new Card("6", "♠"));
        
        // Player 2 has a Flush: 2, 3, 4, 5, 6 of Hearts (but no Straight Flush)
        player2.addCard(new Card("2", "♥"));
        player2.addCard(new Card("3", "♥"));
        
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("7", "♠"));
        communityCards.add(new Card("8", "♠"));
        communityCards.add(new Card("9", "♠"));
        
        String p1Result = player1.playHand(communityCards);
        String p2Result = player2.playHand(communityCards);
        
        String winner = Game.determineWinner(player1, player2, p1Result, p2Result, communityCards);
        System.out.println(winner);
    }
}
