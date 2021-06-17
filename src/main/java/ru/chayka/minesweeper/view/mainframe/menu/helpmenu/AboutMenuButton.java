package ru.chayka.minesweeper.view.mainframe.menu.helpmenu;

import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.UnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;

public class AboutMenuButton {
    private final JMenuItem jMenuItem;

    private final UnparameterizedButtonPressedEventSender unparameterizedButtonPressedEventSender;

    public AboutMenuButton() {
        jMenuItem = new JMenuItem("About...");

        unparameterizedButtonPressedEventSender = new UnparameterizedButtonPressedEventSender();

        jMenuItem.addActionListener(event ->
                unparameterizedButtonPressedEventSender.notifyAllListeners(
                        new UnparameterizedButtonPressedEvent(UnparameterizedButton.ABOUT)));
    }

    public JMenuItem getJMenuItem() {
        return jMenuItem;
    }

    public UnparameterizedButtonPressedEventSender getUnparameterizedButtonPressedEventSender() {
        return unparameterizedButtonPressedEventSender;
    }
}