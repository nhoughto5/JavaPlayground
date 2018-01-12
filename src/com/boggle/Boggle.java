package com.boggle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Square{
    public boolean visited = false;
    public int m, n;
    public char letter;
    public Square(int row, int col, char ch){
        this.m = row;
        this.n = col;
        this.letter = ch;
    }
}

public class Boggle {
    private Square[][] board;
    private List<String> words;
    private int rowSize = 0, colSize = 0;

    private String depthSearch(int row, int col, String str){
        this.board[row][col].visited = true;
        str += Character.toString(this.board[row][col].letter);
        if(this.words.contains(str)){
            System.out.println("Found word: " + str);
        }

        for(int i = row-1; i <= row+1 && i < 3; i ++){
            for(int j = col-1; j <= col+1 && j < 3; j++){
                if(i >= 0 && j >= 0 && !this.board[i][j].visited){
                    this.depthSearch( i, j, str);
                }
            }
        }
        return str;
    }

    private List<String> findWords(List<String> words){
        this.words = words;
        List<String> foundWords = new ArrayList<String>();
        this.resetBoard();
        String str = "";
        for(int i = 0; i < this.rowSize; i++){
            for(int j = 0; j < this.colSize; j++){
                str = depthSearch(i,j, str);
            }
        }
        return foundWords;
    }

    private void resetBoard(){
        for(int i = 0; i < this.rowSize; i++){
            for(int j = 0; j < this.colSize; j++){
                this.board[i][j].visited = false;
            }
        }
    }

    private void populateBoard(int m, int n){
//        this.rowSize = m;
//        this.colSize = n;
        this.board = new Square[3][3];
//        for(int i = 0; i < m; i++){
//            for(int j = 0; j < n; j++){
//                Random r = new Random();
//                this.board[i][j] = new Square(i,j,(char)(r.nextInt(26) + 'a'));
//            }
//        }
        this.board[0][0] = new Square(0,0, 'g');
        this.board[0][1] = new Square(0,1, 'i');
        this.board[0][2] = new Square(0,2, 'z');
        this.board[1][0] = new Square(1,0, 'u');
        this.board[1][1] = new Square(1,1, 'e');
        this.board[1][2] = new Square(1,2, 'k');
        this.board[2][0] = new Square(2,0, 'q');
        this.board[2][1] = new Square(2,1, 's');
        this.board[2][2] = new Square(2,2, 'e');
    }

    public static void main(String[] args){
        List<String> words = new ArrayList<String>();
        words.add("geeks");
        words.add("for");
        words.add("quiz");
        words.add("go");
        Boggle game = new Boggle();
        game.populateBoard(3,3);
        game.findWords(words);
        System.out.println("Done");
    }
}
