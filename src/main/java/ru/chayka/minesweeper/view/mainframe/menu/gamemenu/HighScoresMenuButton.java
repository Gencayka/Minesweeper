package ru.chayka.minesweeper.view.mainframe.menu.gamemenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.UnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;

public class HighScoresMenuButton {
    private static final Logger log = LoggerFactory.getLogger(HighScoresMenuButton.class.getName());

    private final JMenuItem jMenuItem;

    private final UnparameterizedButtonPressedEventSender unparameterizedButtonPressedEventSender;

    public HighScoresMenuButton() {
        jMenuItem = new JMenuItem("High Scores");

        unparameterizedButtonPressedEventSender = new UnparameterizedButtonPressedEventSender();

        jMenuItem.addActionListener(event ->
                unparameterizedButtonPressedEventSender.notifyAllListeners(
                        new UnparameterizedButtonPressedEvent(UnparameterizedButton.HIGH_SCORES)));
    }

    public JMenuItem getJMenuItem() {
        return jMenuItem;
    }

    public UnparameterizedButtonPressedEventSender getUnparameterizedButtonPressedEventSender() {
        return unparameterizedButtonPressedEventSender;
    }
}