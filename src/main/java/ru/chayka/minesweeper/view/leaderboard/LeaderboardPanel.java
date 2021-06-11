package ru.chayka.minesweeper.view.leaderboard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeaderboardPanel {
    private final JPanel jPanel;

    public LeaderboardPanel() {
        jPanel = new JPanel();

        jPanel.setLayout(new GridBagLayout());
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    public void updateLeaderboard(ArrayList<String> strDifficultyModes,
                                  ArrayList<Integer> times,
                                  ArrayList<String> leaderNames) {
        jPanel.removeAll();
        jPanel.revalidate();

        for (int i = 0; i < strDifficultyModes.size(); i++) {
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridy = i;
            gridBagConstraints.anchor = GridBagConstraints.WEST;

            gridBagConstraints.gridx = 0;
            JPanel panel = new JPanel();
            panel.add(new JLabel(strDifficultyModes.get(i) + ":"));
            jPanel.add(panel, gridBagConstraints);

            gridBagConstraints.gridx = 1;
            panel = new JPanel();
            panel.add(new JLabel(times.get(i).toString() + " s"));
            jPanel.add(panel, gridBagConstraints);

            gridBagConstraints.gridx = 2;
            panel = new JPanel();
            panel.add(new JLabel(leaderNames.get(i)));
            jPanel.add(panel, gridBagConstraints);
        }

        jPanel.repaint();
        JDialog parentFrame = (JDialog) SwingUtilities.getWindowAncestor(jPanel);
        parentFrame.pack();
    }
}
