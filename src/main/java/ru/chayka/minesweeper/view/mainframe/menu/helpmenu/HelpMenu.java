package ru.chayka.minesweeper.view.mainframe.menu.helpmenu;

import javax.swing.*;

public class HelpMenu {
    public final JMenu jMenu;

    private final AboutMenuButton aboutMenuButton;

    public HelpMenu() {
        jMenu = new JMenu("Help");

        aboutMenuButton = new AboutMenuButton();
        jMenu.add(aboutMenuButton.getJMenuItem());
    }

    public AboutMenuButton getAboutMenuButton() {
        return aboutMenuButton;
    }

    public JMenu getJMenu() {
        return jMenu;
    }
}