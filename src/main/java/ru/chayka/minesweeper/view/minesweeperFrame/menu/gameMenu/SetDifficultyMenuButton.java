package ru.chayka.minesweeper.view.minesweeperFrame.menu.gameMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.observerInterfaces.observables.view.SetDifficultyButtonObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.StartNewGameButtonObserver;
import ru.chayka.minesweeper.view.MVCLogger;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.util.ArrayList;

public class SetDifficultyMenuButton
        implements SetDifficultyButtonObservable {
    private static final Logger log = LoggerFactory.getLogger(SetDifficultyMenuButton.class.getName());

    private final ViewItem viewItem;
    private final JRadioButtonMenuItem jRadioButtonMenuItem;

    private final ArrayList<StartNewGameButtonObserver> observers;

    public SetDifficultyMenuButton(String text) {
        viewItem = new ViewItem(this);
        jRadioButtonMenuItem = new JRadioButtonMenuItem(text);
        observers = new ArrayList<>();

        jRadioButtonMenuItem.addActionListener(event -> notifyObservers(jRadioButtonMenuItem.getText()));
    }

    public ViewItem getViewComponent() {
        return viewItem;
    }

    public JRadioButtonMenuItem getJRadioButtonMenuItem() {
        return jRadioButtonMenuItem;
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
    public void notifyObservers(String strDifficultyMode) {
        for (var observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.transferToModel(strDifficultyMode);
        }
    }
}