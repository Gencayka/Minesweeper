package ru.chayka.minesweeper.view.minesweeperFrame.menu.gameMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.observerInterfaces.observables.view.HighScoresButtonObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.LeaderboardActionsObserver;
import ru.chayka.minesweeper.view.MVCLogger;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.util.ArrayList;

public class HighScoresMenuButton
        implements HighScoresButtonObservable {
    private static final Logger log = LoggerFactory.getLogger(HighScoresMenuButton.class.getName());

    private final ViewItem viewItem;
    private final JMenuItem jMenuItem;

    private final ArrayList<LeaderboardActionsObserver> observers;

    public HighScoresMenuButton() {
        viewItem = new ViewItem(this);
        jMenuItem = new JMenuItem("High Scores");
        observers = new ArrayList<>();

        jMenuItem.addActionListener(event -> notifyObservers());
    }

    public ViewItem getViewComponent() {
        return viewItem;
    }

    public JMenuItem getJMenuItem() {
        return jMenuItem;
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

    @Override
    public void notifyObservers() {
        for (LeaderboardActionsObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.transferShowLeaderboardCommandToModel();
        }
    }
}