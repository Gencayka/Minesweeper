package ru.chayka.minesweeper.view.mainframe.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.UnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;

public class HelpMenu {
    private static final Logger log = LoggerFactory.getLogger(HelpMenu.class.getName());

    public final JMenu jMenu;

    private final JMenuItem aboutMenuButton;

    private final UnparameterizedButtonPressedEventSender unparameterizedButtonPressedEventSender;

    public HelpMenu() {
        jMenu = new JMenu();
        aboutMenuButton = new JMenuItem();

        unparameterizedButtonPressedEventSender = new UnparameterizedButtonPressedEventSender();

        aboutMenuButton.addActionListener(event ->
                unparameterizedButtonPressedEventSender.notifyAllListeners(
                        new UnparameterizedButtonPressedEvent(UnparameterizedButton.ABOUT)));

        assembleMenu();
    }

    public JMenu getJMenu() {
        return jMenu;
    }

    public UnparameterizedButtonPressedEventSender getUnparameterizedButtonPressedEventSender() {
        return unparameterizedButtonPressedEventSender;
    }

    private void assembleMenu() {
        jMenu.setText("Help");

        aboutMenuButton.setText("About...");
        jMenu.add(aboutMenuButton);

        log.debug("Help Menu is assembled");
    }
}