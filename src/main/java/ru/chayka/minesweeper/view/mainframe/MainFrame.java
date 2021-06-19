package ru.chayka.minesweeper.view.mainframe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.view.mainframe.menu.GameMenuBar;
import ru.chayka.minesweeper.view.mainframe.minefield.MinefieldPanel;
import ru.chayka.minesweeper.view.mainframe.otherelements.OtherElementsPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private static final Logger log = LoggerFactory.getLogger(MainFrame.class.getName());

    private final JFrame jFrame;

    private final GameMenuBar gameMenuBar;
    private final OtherElementsPanel otherElementsPanel;
    private final MinefieldPanel minefieldPanel;

    public MainFrame() {
        jFrame = new JFrame();
        gameMenuBar = new GameMenuBar();
        otherElementsPanel = new OtherElementsPanel();
        minefieldPanel = new MinefieldPanel();

        assembleFrame();
    }

    public GameMenuBar getGameMenuBar() {
        return gameMenuBar;
    }

    public OtherElementsPanel getOtherElementsPanel() {
        return otherElementsPanel;
    }

    public MinefieldPanel getMinefieldPanel() {
        return minefieldPanel;
    }

    public JFrame getJFrame() {
        return jFrame;
    }

    private void assembleFrame() {
        jFrame.setTitle("Minesweeper");
        jFrame.setLayout(new GridBagLayout());
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints menuConstraints = new GridBagConstraints();
        menuConstraints.gridy = 0;
        menuConstraints.anchor = GridBagConstraints.WEST;
        menuConstraints.fill = GridBagConstraints.HORIZONTAL;
        jFrame.add(gameMenuBar.getJMenuBar(), menuConstraints);

        GridBagConstraints otherElementsPanelConstraints = new GridBagConstraints();
        otherElementsPanelConstraints.gridy = 1;
        otherElementsPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        jFrame.add(otherElementsPanel.getJPanel(), otherElementsPanelConstraints);

        GridBagConstraints minefieldConstraints = new GridBagConstraints();
        minefieldConstraints.gridy = 2;
        jFrame.add(minefieldPanel.getJPanel(), minefieldConstraints);

        jFrame.pack();

        log.debug("Main Frame is assembled");
    }
}
