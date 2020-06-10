package tictactoe;

import java.util.Scanner;

public class Main {
    public static boolean isEmptyCells(char[][] field) {
        boolean answer = false;

        for (int i = 0; i < 3 && !answer; i++) {
            for (int j = 0; j < 3 && !answer; j++) {
                answer = field[i][j] == ' ';
            }
        }

        return answer;
    }

    public static boolean isRow(char[][] field, char ox) {
        boolean answer = false;

        for (int i = 0; i < 3 && !answer; i++) {

            boolean tempAnswer = true;
            for (int j = 0; j < 3 && tempAnswer; j++) {
                tempAnswer = field[i][j] == ox;
            }

            if (tempAnswer) {
                answer = true;
            }
        }

        for (int i = 0; i < 3 && !answer; i++) {

            boolean tempAnswer = true;
            for (int j = 0; j < 3 && tempAnswer; j++) {
                tempAnswer = field[j][i] == ox;
            }

            if (tempAnswer) {
                answer = true;
            }
        }

        if (!answer) {
            answer = field[0][0] == ox && field[1][1] == ox && field[2][2] == ox
                    ||
                    field[0][2] == ox && field[1][1] == ox && field[2][0] == ox;
        }

        return answer;
    }

    public static int count(char[][] field, char ox) {
        int answer = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ox) {
                    answer++;
                }
            }
        }

        return answer;
    }

    //this function analyze field and return whether it finished
    public static boolean analyzeField(char[][] field) {
        if (isRow(field, 'O')) {
            System.out.println("O wins");

            return true;
        } else if (isRow(field, 'X')) {
            System.out.println("X wins");

            return true;
        } else if (isEmptyCells(field)) {

            return false;
        } else {
            System.out.println("Draw");

            return true;
        }
    }

    public static char[][] toMatrix(String sequence) {
        char[][] field = new char[3][3];

        field[0][0] = sequence.charAt(0);
        field[0][1] = sequence.charAt(1);
        field[0][2] = sequence.charAt(2);
        field[1][0] = sequence.charAt(3);
        field[1][1] = sequence.charAt(4);
        field[1][2] = sequence.charAt(5);
        field[2][0] = sequence.charAt(6);
        field[2][1] = sequence.charAt(7);
        field[2][2] = sequence.charAt(8);

        return field;
    }

    public static char[][] emptyField() {
        char[][] field = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }

        return field;
    }

    public static void outField(char[][] field) {
        System.out.println("---------");
        System.out.println("| " + field[0][0] + " " + field[0][1] + " " + field[0][2] + " |");
        System.out.println("| " + field[1][0] + " " + field[1][1] + " " + field[1][2] + " |");
        System.out.println("| " + field[2][0] + " " + field[2][1] + " " + field[2][2] + " |");
        System.out.println("---------");
    }

    public static boolean isInt(String line) {
        boolean answer = true;


        for (int i = 0; i < line.length() && answer; i++) {
            answer = line.charAt(i) <= '9' && line.charAt(i) >= '0';
        }

        return answer;
    }

    public static boolean isOccupied(char[][] field, int first, int second) {
        return field[3 - second][--first] != ' ';
    }

    public static boolean isOutOfBorder(int first, int second) {
        return first < 1 || first > 3 || second < 1 || second > 3;
    }

    public static void move(char[][] field, char ox) {
        Scanner scanner = new Scanner(System.in);

        boolean isOk = false;

        while (!isOk) {
            System.out.print("Enter the coordinates: ");

            String first = scanner.next();
            String second = scanner.next();

            if (! isInt(first) || ! isInt(second)) {
                System.out.println("You should enter numbers!");
            } else if (isOutOfBorder(Integer.parseInt(first), Integer.parseInt(second))) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (isOccupied(field, Integer.parseInt(first), Integer.parseInt(second))) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                field[3 - Integer.parseInt(second)][Integer.parseInt(first) - 1] = ox;
                isOk = true;
            }
        }
    }

    public static void gameTicTacToe() {
        char[][] field = emptyField();
        outField(field);

        boolean oxSwitcher = true;
        while (! analyzeField(field)) {
            System.out.print("Enter the coordinates: ");

            move(field, oxSwitcher ? 'X' : 'O');
            outField(field);

            oxSwitcher = !oxSwitcher;
        }
    }

    public static void main(String[] args) {
        gameTicTacToe();
    }
}
