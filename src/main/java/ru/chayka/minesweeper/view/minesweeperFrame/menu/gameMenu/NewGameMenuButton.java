package ru.chayka.minesweeper.view.minesweeperFrame.menu.gameMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.observerInterfaces.observables.view.StartNewGameButtonObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.StartNewGameButtonObserver;
import ru.chayka.minesweeper.view.MVCLogger;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.util.ArrayList;

public class NewGameMenuButton
        implements StartNewGameButtonObservable {
    private static final Logger log = LoggerFactory.getLogger(NewGameMenuButton.class.getName());

    private final ViewItem viewItem;
    private final JMenuItem jMenuItem;

    private final ArrayList<StartNewGameButtonObserver> observers;

    public NewGameMenuButton() {
        viewItem = new ViewItem(this);
        jMenuItem = new JMenuItem("New Game");
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
    public void registerObserver(StartNewGameButtonObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(StartNewGameButtonObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (StartNewGameButtonObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.transferToModel();
        }
    }
}