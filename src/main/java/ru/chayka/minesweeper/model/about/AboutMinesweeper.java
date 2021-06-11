package ru.chayka.minesweeper.model.about;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.observerInterfaces.observables.model.AboutMinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.AboutMinesweeperObserver;

import java.util.ArrayList;

public class AboutMinesweeper
        implements AboutMinesweeperObservable {
    private static final Logger log = LoggerFactory.getLogger(AboutMinesweeper.class.getName());

    private final ArrayList<AboutMinesweeperObserver> observers;

    private final String text = "Here could be your advertising";

    public AboutMinesweeper() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(AboutMinesweeperObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(AboutMinesweeperObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (AboutMinesweeperObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.acceptAboutData(text);
        }
    }
}
