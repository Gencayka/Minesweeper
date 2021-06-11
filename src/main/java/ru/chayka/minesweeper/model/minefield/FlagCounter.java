package ru.chayka.minesweeper.model.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.observerInterfaces.observables.model.FlagCounterObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.FlagCounterObserver;

import java.util.ArrayList;

public class FlagCounter
        implements FlagCounterObservable {
    private static final Logger log = LoggerFactory.getLogger(FlagCounter.class.getName());

    private final ArrayList<FlagCounterObserver> observers;

    private int numOfRemainingMines;

    public FlagCounter() {
        observers = new ArrayList<>();
        numOfRemainingMines = 0;
    }

    public void setNumOfRemainingMines(DifficultyMode difficultyMode) {
        setNumOfRemainingMines(difficultyMode.numOfMines);
    }

    public void setNumOfRemainingMines(int numOfRemainingMines) {
        this.numOfRemainingMines = numOfRemainingMines;
        notifyObservers();
    }

    public void increaseNumOfMines() {
        log.debug("Flag counter increased");
        numOfRemainingMines++;
        notifyObservers();
    }

    public void decreaseNumOfMines() {
        log.debug("Flag counter decreased");
        numOfRemainingMines--;
        notifyObservers();
    }

    @Override
    public void registerObserver(FlagCounterObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(FlagCounterObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (var observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.update(numOfRemainingMines);
        }
    }
}
