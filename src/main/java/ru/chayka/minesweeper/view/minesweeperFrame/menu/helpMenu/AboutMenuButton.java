package ru.chayka.minesweeper.view.minesweeperFrame.menu.helpMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.observerInterfaces.observables.view.AboutButtonObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.AboutButtonObserver;
import ru.chayka.minesweeper.view.MVCLogger;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.util.ArrayList;

public class AboutMenuButton
        implements AboutButtonObservable {
    private static final Logger log = LoggerFactory.getLogger(AboutMenuButton.class.getName());

    private final ViewItem viewComponent;
    private final JMenuItem jMenuItem;

    private final ArrayList<AboutButtonObserver> observers;

    public AboutMenuButton() {
        viewComponent = new ViewItem(this);
        jMenuItem = new JMenuItem("About...");
        observers = new ArrayList<>();

        jMenuItem.addActionListener(event -> notifyObservers());
    }

    public ViewItem getViewComponent() {
        return viewComponent;
    }

    public JMenuItem getJMenuItem() {
        return jMenuItem;
    }

    @Override
    public void registerObserver(AboutButtonObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(AboutButtonObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (AboutButtonObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.transferToModel();
        }
    }
}