
/*
This code is a part of Youtube Tutorials by Anurag Codes (https://www.youtube.com/channel/UC2jUUYFTA2byJXqmONRv8aw)
if you have any doubts regarding the codes you can ping me on instagram.com/anuragcodes or Comment in the video section

MAIN POINTS :
1. Check the cell to be '-' of first character of the word
2. Check if that particular word can be filled in That place horizontally or vertically
3. If the word can be filled , fill it!
4. If the word cannot be filled, Backtrack and remove the previous word filled and fill it in some other position
5. Repeat the process until and unless all the words are filled that is index == words.length.
Happy coding :)

*/

import java.util.*;

public class CrosswordTutorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String crossword_temp[] = new String[10];
        // inputting the 10 lines of crossword
        for (int i = 0; i < 10; i++) {
            crossword_temp[i] = sc.nextLine();
        }

        // converting strings to character Array beacuse strings in java are immutable
        char crossword[][] = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                crossword[i][j] = crossword_temp[i].charAt(j);
            }
        }

        // inputting the words separated by " ; "
        String words[] = sc.nextLine().split(";");

        // wordfill array contains if the particular character of a word was inserted or
        // not
        boolean wordfill[][] = new boolean[words.length][];
        for (int i = 0; i < words.length; i++) {
            wordfill[i] = new boolean[words[i].length()];
        }

        solveCrossword(crossword, words, 0, wordfill);
        sc.close();
    }

    // this function solves the crossword
    private static void solveCrossword(char[][] crossword, String[] words, int index, boolean wordFill[][]) {
        // System.out.println("index :" + index);
        if (index == words.length) {
            printCrossword(crossword);
            return;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (crossword[i][j] == '-' || crossword[i][j] == words[index].charAt(0)) {
                    // try to insert Vertically
                    boolean inserted = insertVerticalIfPossible(i, j, crossword, words, index, wordFill);
                    if (inserted) solveCrossword(crossword, words, index + 1, wordFill);
                    if (inserted)removeVertically(i, j, crossword, words, index, wordFill);

                    // try to insert Horizontally
                    inserted = insertHorizontalIfPossible(i, j, crossword, words, index, wordFill);
                    if (inserted)solveCrossword(crossword, words, index + 1, wordFill);
                    if (inserted)removeHorizontally(i, j, crossword, words, index, wordFill);

                }
            }
        }

    }

    private static void removeHorizontally(int i, int j, char[][] crossword, String[] words, int index,
                                           boolean[][] wordFill) {
        // System.out.println("removing Horizontally:"+ words[index]);
        for (int x = 0; x < words[index].length(); x++) {
            if (wordFill[index][x]) {
                crossword[i][j] = '-';
            }
            wordFill[index][x] = false;
            j++;
        }

    }

    private static boolean insertHorizontalIfPossible(int i, int j, char[][] crossword, String[] words, int index,
                                                      boolean[][] wordFill) {

        String word = words[index];
        // System.out.println("trying adding : " + word + " i:"+i +" j:"+j);

        int wordIndex = 0, initial_j = j;
        while (j < 10) {
            if (crossword[i][j] == '+')
                return false;
            else if (crossword[i][j] == '-')
                wordIndex++;
            else if (crossword[i][j] == word.charAt(wordIndex))
                wordIndex++;
            else
                return false;

            // if the word index reaches word.length() that means the word can be inserted
            // here so we will!
            if (wordIndex == word.length()) {
                // System.out.println(word + " can be added at i :" + i + " j:" + initial_j);
                for (int x = 0; x < word.length(); x++) {
                    // System.out.println("ini j " + initial_j);
                    if (crossword[i][initial_j] == word.charAt(x)) {
                        wordIndex++;
                    } else {
                        crossword[i][initial_j] = word.charAt(x);
                        wordFill[index][x] = true;
                    }
                    initial_j++;
                }
                // printCrossword(crossword);
                return true;
            }
            j++;
        }
        return false;

    }

    private static void removeVertically(int i, int j, char[][] crossword, String[] words, int index,
                                         boolean[][] wordFill) {
        // System.out.println("removing Vertically :"+ words[index]);

        for (int x = 0; x < words[index].length(); x++) {
            if (wordFill[index][x]) {
                crossword[i][j] = '-';
            }
            wordFill[index][x] = false;
            i++;
        }
    }

    private static boolean insertVerticalIfPossible(int i, int j, char[][] crossword, String[] words, int index,
                                                    boolean[][] wordFill) {
        //  System.out.println("trying adding : "+ words[index] +" i: "+i+" j:"+j);
        String word = words[index];
        int wordIndex = 0, initial_i = i;
        while (i < 10) {
            if (crossword[i][j] == '+')
                return false;
            else if (crossword[i][j] == '-')
                wordIndex++;
            else if (crossword[i][j] == word.charAt(wordIndex))
                wordIndex++;
            else
                return false;

            // if the word index reaches word.length() that means the word can be inserted
            // here so we will!
            if (wordIndex == word.length()) {
                // System.out.println(word + " can be added at i :" + initial_i + " j:" + j);
                for (int x = 0; x < word.length(); x++) {
                    if (crossword[initial_i][j] == word.charAt(x)) {
                        wordIndex++;
                    } else {
                        crossword[initial_i][j] = word.charAt(x);
                        wordFill[index][x] = true;
                    }
                    initial_i++;
                }
                // printCrossword(crossword);

                return true;
            }
            i++;
        }
        return false;
    }

    // this function prints the crossword after it has been filled!
    private static void printCrossword(char[][] crossword) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(crossword[i][j]);
            }
            System.out.println();
        }
    }
}
