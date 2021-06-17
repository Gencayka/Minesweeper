package ru.chayka.minesweeper.view.mainframe.menu;

import ru.chayka.minesweeper.view.mainframe.menu.gamemenu.GameMenu;
import ru.chayka.minesweeper.view.mainframe.menu.helpmenu.HelpMenu;

import javax.swing.*;

public class GameMenuBar {
    private final JMenuBar jMenuBar;

    private final GameMenu gameMenu;
    private final HelpMenu helpMenu;

    public GameMenuBar() {
        jMenuBar = new JMenuBar();

        gameMenu = new GameMenu();
        jMenuBar.add(gameMenu.getJMenu());

        helpMenu = new HelpMenu();
        jMenuBar.add(helpMenu.getJMenu());
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
}