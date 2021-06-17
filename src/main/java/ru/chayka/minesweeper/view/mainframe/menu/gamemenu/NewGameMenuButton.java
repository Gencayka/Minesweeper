package ru.chayka.minesweeper.view.mainframe.menu.gamemenu;

import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.UnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;

public class NewGameMenuButton {
    private final JMenuItem jMenuItem;

    private final UnparameterizedButtonPressedEventSender unparameterizedButtonPressedEventSender;

    public NewGameMenuButton() {
        jMenuItem = new JMenuItem("New Game");

        unparameterizedButtonPressedEventSender = new UnparameterizedButtonPressedEventSender();

        jMenuItem.addActionListener(event ->
                unparameterizedButtonPressedEventSender.notifyAllListeners(
                        new UnparameterizedButtonPressedEvent(UnparameterizedButton.NEW_GAME)));
    }

    public JMenuItem getJMenuItem() {
        return jMenuItem;
    }

    public UnparameterizedButtonPressedEventSender getUnparameterizedButtonPressedEventSender() {
        return unparameterizedButtonPressedEventSender;
    }
}