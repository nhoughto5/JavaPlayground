package com.boggle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boggle {

    private static List<String> findWords(char[][] board, List<String> words){
        List<String> foundWords = new ArrayList<String>();

        return foundWords;
    }

    private static char[][] populateBoard(int m, int n){
        char[][] board = new char[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                Random r = new Random();
                board[i][j] = (char)(r.nextInt(26) + 'a');
            }
        }
        return board;
    }

    public static void main(String[] args){
        List<String> words = new ArrayList<String>();
        words.add("Hello");
        words.add("foo");
        words.add("bar");
        findWords(populateBoard(3,4), words);
    }
}
