package ru.chayka.minesweeper.model.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.model.MvcFlagCounterDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.model.MvcGameOverEvent;
import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldCellDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcFlagCounterDtoEventSender;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcGameOverEventSender;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcMinefieldCellDtoEventSender;
import ru.chayka.minesweeper.model.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.model.eventsystem.events.FirstCellOpeningEvent;
import ru.chayka.minesweeper.model.eventsystem.events.GameOverEvent;
import ru.chayka.minesweeper.model.eventsystem.events.MinefieldDtoEvent;
import ru.chayka.minesweeper.model.eventsystem.listeners.MinefieldDtoEventListener;
import ru.chayka.minesweeper.model.eventsystem.senders.FirstCellOpeningEventSender;
import ru.chayka.minesweeper.model.eventsystem.senders.GameOverEventSender;

public class MinefieldActionPerformer
        implements MinefieldDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(MinefieldActionPerformer.class.getName());

    private Minefield minefield;
    private final FirstCellOpeningEventSender firstCellOpeningEventSender;
    private final MvcMinefieldCellDtoEventSender mvcMinefieldCellDtoEventSender;
    private final GameOverEventSender gameOverEventSender;
    private final MvcFlagCounterDtoEventSender mvcFlagCounterDtoEventSender;
    private final MvcGameOverEventSender mvcGameOverEventSender;
    private int numOfRemainingMines;

    public MinefieldActionPerformer() {
        firstCellOpeningEventSender = new FirstCellOpeningEventSender();
        gameOverEventSender = new GameOverEventSender();

        mvcMinefieldCellDtoEventSender = new MvcMinefieldCellDtoEventSender();
        mvcFlagCounterDtoEventSender = new MvcFlagCounterDtoEventSender();
        mvcGameOverEventSender = new MvcGameOverEventSender();
    }

    public void setMinefield(Minefield minefield) {
        this.minefield = minefield;
    }

    public FirstCellOpeningEventSender getFirstCellOpeningEventSender() {
        return firstCellOpeningEventSender;
    }

    public GameOverEventSender getGameOverEventSender() {
        return gameOverEventSender;
    }

    public MvcMinefieldCellDtoEventSender getMvcMinefieldCellDtoEventSender() {
        return mvcMinefieldCellDtoEventSender;
    }

    public MvcFlagCounterDtoEventSender getMvcFlagCounterDtoEventSender() {
        return mvcFlagCounterDtoEventSender;
    }

    public MvcGameOverEventSender getMvcGameOverEventSender() {
        return mvcGameOverEventSender;
    }

    public void openTheCell(MinefieldCell fieldCell) {
        if (!minefield.isMined()) {
            firstCellOpeningEventSender.notifyAllListeners(new FirstCellOpeningEvent());
            MinefieldMinesSetter minesSetter = new MinefieldMinesSetter(minefield);
            minesSetter.generateMines(fieldCell.getRow(), fieldCell.getColumn());
        }

        if (fieldCell.isMined()) {
            loseTheGame(fieldCell);
        } else {
            fieldCell.setOpened(true);
            mvcMinefieldCellDtoEventSender.notifyAllListeners(
                    new MvcMinefieldCellDtoEvent(fieldCell));

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
        mvcMinefieldCellDtoEventSender.notifyAllListeners(
                new MvcMinefieldCellDtoEvent(fieldCell));
        decreaseNumOfMines();
    }

    public void flagTheCell(int row, int column) {
        flagTheCell(minefield.getFieldCell(row, column));
    }

    public void unflagTheCell(MinefieldCell fieldCell) {
        fieldCell.setFlagged(false);
        mvcMinefieldCellDtoEventSender.notifyAllListeners(
                new MvcMinefieldCellDtoEvent(fieldCell));
        increaseNumOfMines();
    }

    public void unflagTheCell(int row, int column) {
        unflagTheCell(minefield.getFieldCell(row, column));
    }

    private void increaseNumOfMines() {
        numOfRemainingMines++;
        mvcFlagCounterDtoEventSender.notifyAllListeners(
                new MvcFlagCounterDtoEvent(numOfRemainingMines));
    }

    private void decreaseNumOfMines() {
        numOfRemainingMines--;
        mvcFlagCounterDtoEventSender.notifyAllListeners(
                new MvcFlagCounterDtoEvent(numOfRemainingMines));
    }

    private void winTheGame() {
        for (int currentRow = 0; currentRow < minefield.getNumOfRows(); currentRow++) {
            for (int currentColumn = 0; currentColumn < minefield.getNumOfColumns(); currentColumn++) {
                MinefieldCell currentCell = minefield.getFieldCell(currentRow, currentColumn);
                if (currentCell.isMined() && !currentCell.isFlagged()) {
                    currentCell.setFlagged(true);
                    mvcMinefieldCellDtoEventSender.notifyAllListeners(
                            new MvcMinefieldCellDtoEvent(currentCell));
                }
            }
        }

        gameOverEventSender.notifyAllListeners(new GameOverEvent(true, minefield.getDifficultyMode()));
        mvcGameOverEventSender.notifyAllListeners(new MvcGameOverEvent(true));
    }

    private void loseTheGame(MinefieldCell openedMinedCell) {
        for (int currentRow = 0; currentRow < minefield.getNumOfRows(); currentRow++) {
            for (int currentColumn = 0; currentColumn < minefield.getNumOfColumns(); currentColumn++) {
                MinefieldCell currentCell = minefield.getFieldCell(currentRow, currentColumn);
                if (currentCell.isMined() && !currentCell.isFlagged()) {
                    currentCell.setOpened(true);
                    mvcMinefieldCellDtoEventSender.notifyAllListeners(
                            new MvcMinefieldCellDtoEvent(currentCell));
                }
                if (!currentCell.isMined() && currentCell.isFlagged()) {
                    currentCell.setOpened(true);
                    mvcMinefieldCellDtoEventSender.notifyAllListeners(
                            new MvcMinefieldCellDtoEvent(currentCell));
                }
            }
        }


        mvcMinefieldCellDtoEventSender.notifyAllListeners(
                new MvcMinefieldCellDtoEvent(openedMinedCell, true));
        gameOverEventSender.notifyAllListeners(new GameOverEvent(false, minefield.getDifficultyMode()));
        mvcGameOverEventSender.notifyAllListeners(new MvcGameOverEvent(false));
    }

    @Override
    public void acceptEvent(MinefieldDtoEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        minefield = event.getMinefield();
        numOfRemainingMines = event.getMinefield().getNumOfMines();
        mvcFlagCounterDtoEventSender.notifyAllListeners(
                new MvcFlagCounterDtoEvent(numOfRemainingMines));
    }
}
