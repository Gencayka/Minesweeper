package ru.chayka.minesweeper.view.mainframe.menu;

import ru.chayka.minesweeper.eventsystem.events.view.MvcDifficultyModeButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.MvcDifficultyModeButtonPressedEventSender;

import javax.swing.*;

public class SetDifficultyMenuButton {
    private final JRadioButtonMenuItem jRadioButtonMenuItem;

    private final MvcDifficultyModeButtonPressedEventSender mvcDifficultyModeButtonPressedEventSender;

    public SetDifficultyMenuButton(String text) {
        jRadioButtonMenuItem = new JRadioButtonMenuItem(text);

        mvcDifficultyModeButtonPressedEventSender = new MvcDifficultyModeButtonPressedEventSender();

        jRadioButtonMenuItem.addActionListener(event ->
                mvcDifficultyModeButtonPressedEventSender.notifyAllListeners(
                        new MvcDifficultyModeButtonPressedEvent(jRadioButtonMenuItem.getText())));
    }

    public JRadioButtonMenuItem getJRadioButtonMenuItem() {
        return jRadioButtonMenuItem;
    }

    public MvcDifficultyModeButtonPressedEventSender getDifficultyModeButtonPressedEventSender() {
        return mvcDifficultyModeButtonPressedEventSender;
    }
}