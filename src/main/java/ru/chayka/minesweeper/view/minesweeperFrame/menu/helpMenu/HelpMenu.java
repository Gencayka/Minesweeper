package ru.chayka.minesweeper.view.minesweeperFrame.menu.helpMenu;

import ru.chayka.minesweeper.view.compositeClasses.ViewComposite;

import javax.swing.*;

public class HelpMenu {
    private final ViewComposite viewComposite;
    public final JMenu jMenu;

    public HelpMenu() {
        viewComposite = new ViewComposite(this);
        jMenu = new JMenu("Help");

        AboutMenuButton aboutMenuButton = new AboutMenuButton();
        jMenu.add(aboutMenuButton.getJMenuItem());
        viewComposite.addViewComponent(aboutMenuButton.getViewComponent());
    }

    public ViewComposite getViewComponent() {
        return viewComposite;
    }

    public JMenu getJMenu() {
        return jMenu;
    }
}