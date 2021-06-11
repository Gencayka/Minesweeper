package ru.chayka.minesweeper.view.minesweeperFrame;

import ru.chayka.minesweeper.view.compositeClasses.ViewComposite;
import ru.chayka.minesweeper.view.minesweeperFrame.menu.GameMenuBar;
import ru.chayka.minesweeper.view.minesweeperFrame.minefield.MinefieldPanel;
import ru.chayka.minesweeper.view.minesweeperFrame.otherElements.OtherElementsPanel;

import javax.swing.*;
import java.awt.*;

public class MinesweeperFrame {
    private final ViewComposite viewComposite;
    private final JFrame jFrame;

    public MinesweeperFrame() {
        viewComposite = new ViewComposite(this);
        jFrame = new JFrame();

        jFrame.setTitle("Minesweeper");
        jFrame.setLayout(new GridBagLayout());
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Menu Bar
        GridBagConstraints menuConstraints = new GridBagConstraints();
        menuConstraints.gridy = 0;
        menuConstraints.anchor = GridBagConstraints.WEST;
        menuConstraints.fill = GridBagConstraints.HORIZONTAL;

        GameMenuBar gameMenuBar = new GameMenuBar();
        jFrame.add(gameMenuBar.getJMenuBar(), menuConstraints);
        viewComposite.addViewComponent(gameMenuBar.getViewComponent());

        //Other Elements Panel
        GridBagConstraints otherElementsPanelConstraints = new GridBagConstraints();
        otherElementsPanelConstraints.gridy = 1;
        otherElementsPanelConstraints.fill = GridBagConstraints.HORIZONTAL;

        OtherElementsPanel otherElementsPanel = new OtherElementsPanel();
        jFrame.add(otherElementsPanel.getJPanel(), otherElementsPanelConstraints);
        viewComposite.addViewComponent(otherElementsPanel.getViewComponent());

        //Minefield
        GridBagConstraints minefieldConstraints = new GridBagConstraints();
        minefieldConstraints.gridy = 2;

        MinefieldPanel minefieldPanel = new MinefieldPanel();
        jFrame.add(minefieldPanel.getJPanel(), minefieldConstraints);
        viewComposite.addViewComponent(minefieldPanel.getViewComposite());

        jFrame.pack();
    }

    public ViewComposite getViewComponent() {
        return viewComposite;
    }

    public JFrame getJFrame() {
        return jFrame;
    }
}
