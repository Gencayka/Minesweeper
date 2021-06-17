package ru.chayka.minesweeper.model.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.model.FlagCounterDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.FlagCounterDtoEventSender;
import ru.chayka.minesweeper.model.DifficultyMode;

public class FlagCounter {
    private static final Logger log = LoggerFactory.getLogger(FlagCounter.class.getName());

    private int numOfRemainingMines;

    private final FlagCounterDtoEventSender flagCounterDtoEventSender;

    public FlagCounter() {
        numOfRemainingMines = 0;

        flagCounterDtoEventSender = new FlagCounterDtoEventSender();
    }

    public void setNumOfRemainingMines(DifficultyMode difficultyMode) {
        setNumOfRemainingMines(difficultyMode.numOfMines);
    }

    public void setNumOfRemainingMines(int numOfRemainingMines) {
        this.numOfRemainingMines = numOfRemainingMines;
        flagCounterDtoEventSender.notifyAllListeners(
                new FlagCounterDtoEvent(numOfRemainingMines));
    }

    public FlagCounterDtoEventSender getFlagCounterDtoEventSender() {
        return flagCounterDtoEventSender;
    }

    public void increaseNumOfMines() {
        log.debug("Flag counter increased");
        flagCounterDtoEventSender.notifyAllListeners(
                new FlagCounterDtoEvent(++numOfRemainingMines));
    }

    public void decreaseNumOfMines() {
        log.debug("Flag counter decreased");
        flagCounterDtoEventSender.notifyAllListeners(
                new FlagCounterDtoEvent(--numOfRemainingMines));
    }
}
