package ru.chayka.minesweeper.model.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.observerInterfaces.observables.model.RecordNewLeaderNotificatorObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.RecordNewLeaderNotificatorObserver;

import java.util.ArrayList;

public class RecordNewLeaderNotificator
        implements RecordNewLeaderNotificatorObservable {
    private static final Logger log = LoggerFactory.getLogger(RecordNewLeaderNotificator.class.getName());

    private final ArrayList<RecordNewLeaderNotificatorObserver> observers;

    public RecordNewLeaderNotificator() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(RecordNewLeaderNotificatorObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(RecordNewLeaderNotificatorObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObservers(String strDifficultyMode) {
        for (var observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.recordNewLeader(strDifficultyMode);
        }
    }
}
