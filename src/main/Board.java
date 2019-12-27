package main;

public class Board {
    private int[][] board;
    private int[][] answer;
    private int[][] defaultBoard;

    public int[][] getDefaultBoard() {
        return defaultBoard;
    }

    public Board() {
        defaultBoard = new int[][]{
                {5, 4, 0, 0, 2, 0, 8, 0, 6},
                {0, 1, 9, 0, 0, 7, 0, 0, 3},
                {0, 0, 0, 3, 0, 0, 2, 1, 0},
                {9, 0, 0, 4, 0, 5, 0, 2, 0},
                {0, 0, 1, 0, 0, 0, 6, 0, 4},
                {6, 0, 4, 0, 3, 2, 0, 8, 0},
                {0, 6, 0, 0, 0, 0, 1, 9, 0},
                {4, 0, 2, 0, 0, 9, 0, 0, 5},
                {0, 9, 0, 0, 7, 0, 4, 0, 2}};


        board = new int[][]{
                {5, 4, 0, 0, 2, 0, 8, 0, 6},
                {0, 1, 9, 0, 0, 7, 0, 0, 3},
                {0, 0, 0, 3, 0, 0, 2, 1, 0},
                {9, 0, 0, 4, 0, 5, 0, 2, 0},
                {0, 0, 1, 0, 0, 0, 6, 0, 4},
                {6, 0, 4, 0, 3, 2, 0, 8, 0},
                {0, 6, 0, 0, 0, 0, 1, 9, 0},
                {4, 0, 2, 0, 0, 9, 0, 0, 5},
                {0, 9, 0, 0, 7, 0, 4, 0, 2}};

        answer = new int[][]{
                {5, 4, 3, 9, 2, 1, 8, 7, 6},
                {2, 1, 9, 6, 8, 7, 5, 4, 3},
                {8, 7, 6, 3, 5, 4, 2, 1, 9},
                {9, 8, 7, 4, 6, 5, 3, 2, 1},
                {3, 2, 1, 7, 9, 8, 6, 5, 4},
                {6, 5, 4, 1, 3, 2, 9, 8, 7},
                {7, 6, 5, 2, 4, 3, 1, 9, 8},
                {4, 3, 2, 8, 1, 9, 7, 6, 5},
                {1, 9, 8, 5, 7, 6, 4, 3, 2}};

    }

    public int[][] getBoard() {
        return board;
    }

    public int[][] getAnswer() {
        return answer;
    }

    public int getValue(int row, int column) {
        return board[row][column];
    }

    public int getAnswerValue(int row, int column) {
        return answer[row][column];
    }

    public boolean setValue(int row, int column, int value) {
        if (isValid(row, column, value)) {
            board[row][column] = value;
            return true;
        } else {
            return false;
        }

    }

    public boolean isValid(int row, int column, int value) {
        if (value == 0)
            return true;

        if (board[row][column] == value)
            return true;

        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value)
                return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][column] == value)
                return false;
        }

        int boxRow = row - row % 3;
        int boxColumn = column - column % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRow + i][boxColumn + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameFinished() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public boolean isBoardCorrect() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != answer[i][j])
                    return false;
            }
        }
        return true;
    }

}
