package ru.chayka.minesweeper.view.minesweeperFrame.menu;

import ru.chayka.minesweeper.view.compositeClasses.ViewComposite;
import ru.chayka.minesweeper.view.minesweeperFrame.menu.gameMenu.GameMenu;
import ru.chayka.minesweeper.view.minesweeperFrame.menu.helpMenu.HelpMenu;

import javax.swing.*;

public class GameMenuBar {
    private final ViewComposite viewComposite;
    private final JMenuBar jMenuBar;

    public GameMenuBar() {
        viewComposite = new ViewComposite(this);
        jMenuBar = new JMenuBar();

        GameMenu gameMenu = new GameMenu();
        jMenuBar.add(gameMenu.getJMenu());
        viewComposite.addViewComponent(gameMenu.getViewComponent());

        HelpMenu helpMenu = new HelpMenu();
        jMenuBar.add(helpMenu.getJMenu());
        viewComposite.addViewComponent(helpMenu.getViewComponent());
    }

    public ViewComposite getViewComponent() {
        return viewComposite;
    }

    public JMenuBar getJMenuBar() {
        return jMenuBar;
    }
}