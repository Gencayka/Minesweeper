package ru.chayka.minesweeper.model.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.observerInterfaces.observables.model.GameOverNotificatorObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.GameOverNotificatorObserver;

import java.util.ArrayList;

public class GameOverNotificator
        implements GameOverNotificatorObservable {
    private static final Logger log = LoggerFactory.getLogger(GameOverNotificator.class.getName());

    private final ArrayList<GameOverNotificatorObserver> observers;

    public GameOverNotificator() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(GameOverNotificatorObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(GameOverNotificatorObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObserversToWinTheGame() {
        for (var observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.winTheGame();
        }
    }

    @Override
    public void notifyObserversToLoseTheGame() {
        for (var observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.loseTheGame();
        }
    }
}
