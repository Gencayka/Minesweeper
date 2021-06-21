package ru.chayka.minesweeper.view.mainframe.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.view.MvcUnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.MvcUnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;

public class HelpMenu {
    private static final Logger log = LoggerFactory.getLogger(HelpMenu.class.getName());

    public final JMenu jMenu;

    private final JMenuItem aboutMenuButton;

    private final MvcUnparameterizedButtonPressedEventSender mvcUnparameterizedButtonPressedEventSender;

    public HelpMenu() {
        jMenu = new JMenu();
        aboutMenuButton = new JMenuItem();

        mvcUnparameterizedButtonPressedEventSender = new MvcUnparameterizedButtonPressedEventSender();

        aboutMenuButton.addActionListener(event ->
                mvcUnparameterizedButtonPressedEventSender.notifyAllListeners(
                        new MvcUnparameterizedButtonPressedEvent(UnparameterizedButton.ABOUT)));

        assembleMenu();
    }

    public JMenu getJMenu() {
        return jMenu;
    }

    public MvcUnparameterizedButtonPressedEventSender getMvcUnparameterizedButtonPressedEventSender() {
        return mvcUnparameterizedButtonPressedEventSender;
    }

    private void assembleMenu() {
        jMenu.setText("Help");

        aboutMenuButton.setText("About...");
        jMenu.add(aboutMenuButton);

        log.debug("Help Menu is assembled");
    }
}