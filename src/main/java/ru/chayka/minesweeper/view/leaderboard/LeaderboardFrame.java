package ru.chayka.minesweeper.view.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.LeaderboardDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.LeaderboardDtoEventListener;

import javax.swing.*;
import java.awt.*;

public class LeaderboardFrame
        implements LeaderboardDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(LeaderboardFrame.class.getName());

    private final JDialog jDialog;

    private final JFrame mainFrame;

    private final LeaderboardPanel leaderboardPanel;
    private final ResetResultsButton resetResultsButton;

    public LeaderboardFrame(JFrame mainFrame) {
        jDialog = new JDialog();

        this.mainFrame = mainFrame;

        jDialog.setTitle("Leaderboard");
        jDialog.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;

        leaderboardPanel = new LeaderboardPanel();
        jDialog.add(leaderboardPanel.getJPanel(), gridBagConstraints);

        resetResultsButton = new ResetResultsButton();

        JButton okButton = new JButton("Ok");
        okButton.setFocusable(false);
        okButton.addActionListener(event -> jDialog.setVisible(false));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(resetResultsButton.getJButton());
        buttonPanel.add(okButton);
        gridBagConstraints.gridy = 1;
        jDialog.add(buttonPanel, gridBagConstraints);

        jDialog.setResizable(false);
        jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    }

    public LeaderboardPanel getLeaderboardPanel() {
        return leaderboardPanel;
    }

    public ResetResultsButton getResetResultsButton() {
        return resetResultsButton;
    }

    @Override
    public void acceptEvent(LeaderboardDtoEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);

        leaderboardPanel.updateLeaderboard(
                event.getStrDifficultyModes(),
                event.getTimes(),
                event.getLeaderNames());
        if (!jDialog.isVisible()) {
            jDialog.setLocationRelativeTo(mainFrame);
            jDialog.setVisible(true);
        }
    }
}
