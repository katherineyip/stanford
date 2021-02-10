package edu.stanford.cs108.tetris;

import java.util.Arrays;

public class TetrisBrainLogic extends TetrisLogic {

    private DefaultBrain brain;
    Brain.Move bestMove;

    public TetrisBrainLogic(TetrisUIInterface uiInterface) {
        super(uiInterface);
        brain = new DefaultBrain();
    }

    @Override
    protected void tick(int verb) {
        if (!brainMode || verb!=DOWN) {
            super.tick(verb);
            return;
        }

        // if brainMode is enabled
        if (!gameOn) return;

        if (currentPiece != null) {
            board.undo();
        }
        bestMove = brain.bestMove(board, currentPiece, HEIGHT, null);

        if (bestMove != null) {
            if (!currentPiece.equals(bestMove.piece)) {
                super.tick(ROTATE);
            }

            if (bestMove.x < currentX) {
                super.tick(LEFT);
            }
            if (bestMove.x > currentX) {
                super.tick(RIGHT);
            }
        } else {
            if (bestMove.x < currentX) {
                super.tick(LEFT);
            }
            if (bestMove.x > currentX) {
                super.tick(RIGHT);
            }
        }
        super.tick(DOWN);
    }
}
