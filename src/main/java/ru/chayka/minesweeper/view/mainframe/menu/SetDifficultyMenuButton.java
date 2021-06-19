package ru.chayka.minesweeper.view.mainframe.menu;

import ru.chayka.minesweeper.eventsystem.events.view.DifficultyModeButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.DifficultyModeButtonPressedEventSender;

import javax.swing.*;

public class SetDifficultyMenuButton {
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