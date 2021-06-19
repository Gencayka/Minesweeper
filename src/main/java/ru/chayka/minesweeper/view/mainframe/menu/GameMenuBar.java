package ru.chayka.minesweeper.view.mainframe.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class GameMenuBar {
    private static final Logger log = LoggerFactory.getLogger(GameMenuBar.class.getName());

    private final JMenuBar jMenuBar;

    private final GameMenu gameMenu;
    private final HelpMenu helpMenu;

    public GameMenuBar() {
        jMenuBar = new JMenuBar();
        gameMenu = new GameMenu();
        helpMenu = new HelpMenu();

        assembleMenuBar();
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }

    public HelpMenu getHelpMenu() {
        return helpMenu;
    }

    public JMenuBar getJMenuBar() {
        return jMenuBar;
    }

    private void assembleMenuBar() {
        jMenuBar.add(gameMenu.getJMenu());

        jMenuBar.add(helpMenu.getJMenu());

        log.debug("Game Menu Bar is assembled");
    }
}