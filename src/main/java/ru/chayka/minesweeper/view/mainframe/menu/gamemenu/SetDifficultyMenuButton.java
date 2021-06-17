package ru.chayka.minesweeper.view.mainframe.menu.gamemenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.view.DifficultyModeButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.DifficultyModeButtonPressedEventSender;

import javax.swing.*;

public class SetDifficultyMenuButton {
    private static final Logger log = LoggerFactory.getLogger(SetDifficultyMenuButton.class.getName());

    private final JRadioButtonMenuItem jRadioButtonMenuItem;

    private final DifficultyModeButtonPressedEventSender difficultyModeButtonPressedEventSender;

    public SetDifficultyMenuButton(String text) {
        jRadioButtonMenuItem = new JRadioButtonMenuItem(text);

        difficultyModeButtonPressedEventSender = new DifficultyModeButtonPressedEventSender();

        jRadioButtonMenuItem.addActionListener(event ->
                difficultyModeButtonPressedEventSender.notifyAllListeners(
                        new DifficultyModeButtonPressedEvent(jRadioButtonMenuItem.getText())));
    }

    public JRadioButtonMenuItem getJRadioButtonMenuItem() {
        return jRadioButtonMenuItem;
    }

    public DifficultyModeButtonPressedEventSender getDifficultyModeButtonPressedEventSender() {
        return difficultyModeButtonPressedEventSender;
    }
}