package ru.chayka.minesweeper.model.minefield;

import ru.chayka.minesweeper.eventsystem.events.model.GameOverEvent;
import ru.chayka.minesweeper.eventsystem.events.model.MinefieldCellDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.GameOverEventSender;
import ru.chayka.minesweeper.eventsystem.senders.model.MinefieldCellDtoEventSender;
import ru.chayka.minesweeper.model.leaderboard.Leaderboard;
import ru.chayka.minesweeper.model.timer.GameTimer;

public class MinefieldActionPerformer {
    private Minefield minefield;
    private final GameTimer gameTimer;
    private final FlagCounter flagCounter;
    private final Leaderboard leaderboard;

    private final MinefieldCellDtoEventSender minefieldCellDtoEventSender;
    private final GameOverEventSender gameOverEventSender;

    public MinefieldActionPerformer(GameTimer gameTimer,
                                    FlagCounter flagCounter,
                                    Leaderboard leaderboard) {
        this.gameTimer = gameTimer;
        this.flagCounter = flagCounter;
        this.leaderboard = leaderboard;

        minefieldCellDtoEventSender = new MinefieldCellDtoEventSender();
        gameOverEventSender = new GameOverEventSender();
    }

    public void setMinefield(Minefield minefield) {
        this.minefield = minefield;
    }

    public MinefieldCellDtoEventSender getMinefieldCellDtoEventSender() {
        return minefieldCellDtoEventSender;
    }

    public GameOverEventSender getGameOverEventSender() {
        return gameOverEventSender;
    }

    public void openTheCell(MinefieldCell fieldCell) {
        if (!minefield.isMined()) {
            gameTimer.startGameTimer();
            MinefieldMinesSetter minesSetter = new MinefieldMinesSetter(minefield);
            minesSetter.generateMines(fieldCell.getRow(), fieldCell.getColumn());
        }

        if (fieldCell.isMined()) {
            loseTheGame(fieldCell);
        } else {
            fieldCell.setOpened(true);
            minefieldCellDtoEventSender.notifyAllListeners(
                    new MinefieldCellDtoEvent(fieldCell));

            minefield.decreaseNumOfClosedUnminedCells();
            if (minefield.getNumOfClosedUnminedCells() == 0) {
                winTheGame();
            } else if (fieldCell.getNumOfAdjacentMines() == 0) {
                for (MinefieldCell adjacentCell : fieldCell.adjacentCells) {
                    if (!adjacentCell.isOpened() && !adjacentCell.isFlagged()) {
                        openTheCell(adjacentCell);
                    }
                }
            }
        }
    }

    public void openTheCell(int row, int column) {
        openTheCell(minefield.getFieldCell(row, column));
    }

    public void tryToOpenAdjacentCellsWithFlagsAround(MinefieldCell fieldCell) {
        int flagCounter = 0;
        for (MinefieldCell adjacentCell : fieldCell.adjacentCells) {
            if (adjacentCell.isFlagged()) {
                flagCounter++;
            }
        }
        if (flagCounter == fieldCell.getNumOfAdjacentMines()) {
            for (MinefieldCell adjacentCell : fieldCell.adjacentCells) {
                if (!adjacentCell.isFlagged() && !adjacentCell.isOpened()) {
                    openTheCell(adjacentCell);
                }
            }
        }
    }

    public void tryToOpenAdjacentCellsWithFlagsAround(int row, int column) {
        tryToOpenAdjacentCellsWithFlagsAround(minefield.getFieldCell(row, column));
    }

    public void flagTheCell(MinefieldCell fieldCell) {
        fieldCell.setFlagged(true);
        flagCounter.decreaseNumOfMines();
        minefieldCellDtoEventSender.notifyAllListeners(
                new MinefieldCellDtoEvent(fieldCell));
    }

    public void flagTheCell(int row, int column) {
        flagTheCell(minefield.getFieldCell(row, column));
    }

    public void unflagTheCell(MinefieldCell fieldCell) {
        fieldCell.setFlagged(false);
        flagCounter.increaseNumOfMines();
        minefieldCellDtoEventSender.notifyAllListeners(
                new MinefieldCellDtoEvent(fieldCell));
    }

    public void unflagTheCell(int row, int column) {
        unflagTheCell(minefield.getFieldCell(row, column));
    }

    private void winTheGame() {
        for (int currentRow = 0; currentRow < minefield.getNumOfRows(); currentRow++) {
            for (int currentColumn = 0; currentColumn < minefield.getNumOfColumns(); currentColumn++) {
                MinefieldCell currentCell = minefield.getFieldCell(currentRow, currentColumn);
                if (currentCell.isMined() && !currentCell.isFlagged()) {
                    currentCell.setFlagged(true);
                    minefieldCellDtoEventSender.notifyAllListeners(
                            new MinefieldCellDtoEvent(currentCell));
                }
            }
        }

        gameOverEventSender.notifyAllListeners(new GameOverEvent(true));
        gameTimer.stopGameTimer();
        leaderboard.tryToRecordNewLeader(minefield.getDifficultyMode(), gameTimer.getGameTime());
    }

    private void loseTheGame(MinefieldCell openedMinedCell) {
        for (int currentRow = 0; currentRow < minefield.getNumOfRows(); currentRow++) {
            for (int currentColumn = 0; currentColumn < minefield.getNumOfColumns(); currentColumn++) {
                MinefieldCell currentCell = minefield.getFieldCell(currentRow, currentColumn);
                if (currentCell.isMined() && !currentCell.isFlagged()) {
                    currentCell.setOpened(true);
                    minefieldCellDtoEventSender.notifyAllListeners(
                            new MinefieldCellDtoEvent(currentCell));
                }
                if (!currentCell.isMined() && currentCell.isFlagged()) {
                    currentCell.setOpened(true);
                    minefieldCellDtoEventSender.notifyAllListeners(
                            new MinefieldCellDtoEvent(currentCell));
                }
            }
        }

        minefieldCellDtoEventSender.notifyAllListeners(
                new MinefieldCellDtoEvent(openedMinedCell, true));

        gameOverEventSender.notifyAllListeners(new GameOverEvent(false));
        gameTimer.stopGameTimer();
    }
}
