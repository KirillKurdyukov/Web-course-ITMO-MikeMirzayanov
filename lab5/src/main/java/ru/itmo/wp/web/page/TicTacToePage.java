package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {

    private void action(HttpServletRequest request, Map<String, Object> view) {
        State state = (State) request.getSession().getAttribute("state");

        if (state == null) {
            newGame(request, view);
        } else {
            view.put("state", state);
        }
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        State state = (State) request.getSession().getAttribute("state");
        for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
            String move = e.getKey();
            if (move.startsWith("cell")) {

                state.setMove(move.charAt(move.length() - 2) - '0', move.charAt(move.length() - 1) - '0');
            }
        }
        view.put("state", state);
    }

    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        State state = State.newStateClassic();
        request.getSession().setAttribute("state", state);
        view.put("state", state);
    }

    private static class Move {
        private final int row;
        private final int col;
        private final String value;

        private Move(int row, int col, String value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }


        private int getRow() {
            return row;
        }

        private int getColumn() {
            return col;
        }

        private String getValue() {
            return value;
        }

    }

    public static class State {
        private int count_move = 0;
        private final int SIZE;
        private final int COUNT_CELLS_FOR_WINS;
        private boolean crossesMove = true;
        private String phase = "RUNNING";
        String[][] cells;

        public boolean getCrossesMove() {
            return crossesMove;
        }

        public String[][] getCells() {
            return cells;
        }

        public int getSize() {
            return SIZE;
        }

        public String getPhase() {
            return phase;
        }


        private State(int size, int count) {

            this.SIZE = size;
            this.COUNT_CELLS_FOR_WINS = count;
            this.cells = new String[size][size];


        }

        private static State newStateClassic() {
            return new State(3, 3);
        }

        private int count(int x, int y, Move move) {
            int cnt = 0, param1 = 0, param2 = 0;

            cycle:
            for (int i = 1; i < COUNT_CELLS_FOR_WINS; i++) {
                for (int j = 1; j < COUNT_CELLS_FOR_WINS; j++) {
                    param1 += x;
                    param2 += y;
                    if (move != null && move.getRow() + param1 < SIZE && move.getRow() + param1 >= 0 &&
                            move.getColumn() + param2 < SIZE && move.getColumn() + param2 >= 0 && cells[move.getRow() + param1][move.getColumn() + param2] != null) {
                        if (cells[move.getRow() + param1][move.getColumn() + param2].equals(move.getValue())) {
                            cnt++;
                        } else {
                            break cycle;
                        }
                    } else {
                        break cycle;
                    }
                }
            }
            return cnt;
        }


        void checkWin(Move move) {
            if (count(1, 0, move) + count(-1, 0, move) + 1 >= COUNT_CELLS_FOR_WINS ||
                    count(0, 1, move) + count(0, -1, move) + 1 >= COUNT_CELLS_FOR_WINS ||
                    count(1, 1, move) + count(-1, -1, move) + 1 >= COUNT_CELLS_FOR_WINS ||
                    count(-1, 1, move) + count(1, -1, move) + 1 >= COUNT_CELLS_FOR_WINS) {
                phase = !crossesMove ? "WON_X" : "WON_O";
            } else if (count_move == 9)
                phase = "DRAW";
        }

        private boolean isValid(int i, int j) {
            return i >= 0 && j >= 0 && i < SIZE && j < SIZE && cells[i][j] == null;
        }

        private void setMove(int row, int col) {
            if (phase.equals("RUNNING") && isValid(row, col)) {
                count_move++;
                cells[row][col] = crossesMove ? "X" : "O";
                crossesMove = !crossesMove;
                checkWin(new Move(row, col, cells[row][col]));
            }
        }

    }

}
