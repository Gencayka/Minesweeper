package ru.chayka.minesweeper.view.minesweeperFrame.otherElements.smileButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.observerInterfaces.observables.view.StartNewGameButtonObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.StartNewGameButtonObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.view.GameOverNotificatorObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.view.MinefieldCreatorObserver;
import ru.chayka.minesweeper.view.MVCLogger;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SmileButton
        implements StartNewGameButtonObservable,
        GameOverNotificatorObserver, MinefieldCreatorObserver {
    private static final Logger log = LoggerFactory.getLogger(SmileButton.class.getName());

    private final ViewItem viewItem;
    private final JButton jButton;

    private final ArrayList<StartNewGameButtonObserver> observers;

    public SmileButton() {
        viewItem = new ViewItem(this);
        jButton = new JButton();
        observers = new ArrayList<>();

        jButton.addActionListener(event -> notifyObservers());
        jButton.addActionListener(event -> setState(SmileButtonState.DEFAULT));

        jButton.setPreferredSize(new Dimension(26, 26));
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);

        setState(SmileButtonState.DEFAULT);
        jButton.setPressedIcon(SmileButtonState.PRESSED.icon);
    }

    public ViewItem getViewComponent() {
        return viewItem;
    }

    public JButton getJButton() {
        return jButton;
    }

    public void setState(SmileButtonState state) {
        jButton.setIcon(state.icon);
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

    @Override
    public void winTheGame() {
        setState(SmileButtonState.COOL);
    }

    @Override
    public void loseTheGame() {
        setState(SmileButtonState.DEAD);
    }

    @Override
    public void createNewMinefield(int numOfRows, int numOfColumns) {
        setState(SmileButtonState.DEFAULT);
    }
}