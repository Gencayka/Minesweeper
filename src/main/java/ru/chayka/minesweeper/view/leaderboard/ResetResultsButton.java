package ru.chayka.minesweeper.view.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.observerInterfaces.observables.view.ResetResultsButtonObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.LeaderboardActionsObserver;
import ru.chayka.minesweeper.view.MVCLogger;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.util.ArrayList;

public class ResetResultsButton
        implements ResetResultsButtonObservable {
    private static final Logger log = LoggerFactory.getLogger(ResetResultsButton.class.getName());

    private final ViewItem viewItem;
    private final JButton jButton;

    private final ArrayList<LeaderboardActionsObserver> observers;

    public ResetResultsButton() {
        viewItem = new ViewItem(this);
        jButton = new JButton("Reset results");
        observers = new ArrayList<>();

        jButton.setFocusable(false);
        jButton.addActionListener(event -> notifyObservers());
    }

    public ViewItem getViewComponent() {
        return viewItem;
    }

    public JButton getJButton() {
        return jButton;
    }

    @Override
    public void registerObserver(LeaderboardActionsObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(LeaderboardActionsObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    /*@Override
    public void registerObserver(MinesweeperObserver observer) {
        if (observer instanceof LeaderboardActionsObserver) {
            if (!observers.contains(observer)) {
                observers.add((LeaderboardActionsObserver) observer);
                MVCLogger.logObserverRegistration(log, this, observer);
            }
        } else {
            log.warn("Attempt to register invalid type observer");
        }
    }

    @Override
    public void removeObserver(MinesweeperObserver observer) {
        if (observer instanceof LeaderboardActionsObserver) {
            if (observers.contains(observer)) {
                observers.remove(observer);
                MVCLogger.logObserverRemoving(log, this, observer);
            }
        }
    }*/

    @Override
    public void notifyObservers() {
        for (LeaderboardActionsObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.transferResetLeaderboardCommandToModel();
        }
    }
}
