package ru.chayka.minesweeper.model.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.dto.MinefieldCellDto;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.model.leaderboard.Leaderboard;
import ru.chayka.minesweeper.model.timer.GameTimer;
import ru.chayka.minesweeper.observerInterfaces.observables.model.MinefieldActionPerformerObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.MinefieldActionPerformerObserver;

import java.util.ArrayList;

public class MinefieldActionPerformer
        implements MinefieldActionPerformerObservable {
    private static final Logger log = LoggerFactory.getLogger(MinefieldActionPerformer.class.getName());

    private final ArrayList<MinefieldActionPerformerObserver> observers;
    private final GameOverNotificator gameOverNotificator;

    private Minefield minefield;
    private final GameTimer gameTimer;
    private final FlagCounter flagCounter;
    private final Leaderboard leaderboard;

    public MinefieldActionPerformer(GameTimer gameTimer,
                                    FlagCounter flagCounter,
                                    Leaderboard leaderboard) {
        this.gameTimer = gameTimer;
        this.flagCounter = flagCounter;
        this.leaderboard = leaderboard;

        observers = new ArrayList<>();
        gameOverNotificator = new GameOverNotificator();
    }

    public GameOverNotificator getGameOverNotificator() {
        return gameOverNotificator;
    }

    public void setMinefield(Minefield minefield) {
        this.minefield = minefield;
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
            sendMinefieldCellDto(new MinefieldCellDto(fieldCell));

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
        sendMinefieldCellDto(new MinefieldCellDto(fieldCell));
    }

    public void flagTheCell(int row, int column) {
        flagTheCell(minefield.getFieldCell(row, column));
    }

    public void unflagTheCell(MinefieldCell fieldCell) {
        fieldCell.setFlagged(false);
        flagCounter.increaseNumOfMines();
        sendMinefieldCellDto(new MinefieldCellDto(fieldCell));
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
                    sendMinefieldCellDto(new MinefieldCellDto(currentCell));
                }
            }
        }

        gameOverNotificator.notifyObserversToWinTheGame();
        gameTimer.stopGameTimer();
        leaderboard.tryToRecordNewLeader(minefield.getDifficultyMode(), gameTimer.getGameTime());
    }

    private void loseTheGame(MinefieldCell openedMinedCell) {
        for (int currentRow = 0; currentRow < minefield.getNumOfRows(); currentRow++) {
            for (int currentColumn = 0; currentColumn < minefield.getNumOfColumns(); currentColumn++) {
                MinefieldCell currentCell = minefield.getFieldCell(currentRow, currentColumn);
                if (currentCell.isMined() && !currentCell.isFlagged()) {
                    currentCell.setOpened(true);
                    sendMinefieldCellDto(new MinefieldCellDto(currentCell));
                }
                if (!currentCell.isMined() && currentCell.isFlagged()) {
                    currentCell.setOpened(true);
                    sendMinefieldCellDto(new MinefieldCellDto(currentCell));
                }
            }
        }

        sendMinefieldCellDto(new MinefieldCellDto(openedMinedCell, true));

        gameOverNotificator.notifyObserversToLoseTheGame();
        gameTimer.stopGameTimer();
    }

    @Override
    public void registerObserver(MinefieldActionPerformerObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(MinefieldActionPerformerObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void sendMinefieldCellDto(MinefieldCellDto dto) {
        for (var observer : observers) {
            MVCLogger.logDtoSending(log, this, observer);
            observer.acceptMinefieldCellDto(dto);
        }
    }
}
