package ru.chayka.minesweeper.view.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.LeaderboardDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.LeaderboardDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.view.UnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;
import java.awt.*;

public class LeaderboardFrame
        implements LeaderboardDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(LeaderboardFrame.class.getName());

    private final JDialog jDialog;

    private final JFrame mainFrame;

    private final LeaderboardPanel leaderboardPanel;
    private final JButton resetResultsButton;
    private final JButton okButton;

    private final UnparameterizedButtonPressedEventSender unparameterizedButtonPressedEventSender;

    public LeaderboardFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        jDialog = new JDialog();
        leaderboardPanel = new LeaderboardPanel();
        resetResultsButton = new JButton();
        okButton = new JButton();

        unparameterizedButtonPressedEventSender = new UnparameterizedButtonPressedEventSender();

        okButton.addActionListener(event -> jDialog.setVisible(false));
        resetResultsButton.addActionListener(event ->
                unparameterizedButtonPressedEventSender.notifyAllListeners(
                        new UnparameterizedButtonPressedEvent(UnparameterizedButton.RESET_HIGH_SCORES)));

        assembleFrame();
    }

    public UnparameterizedButtonPressedEventSender getUnparameterizedButtonPressedEventSender() {
        return unparameterizedButtonPressedEventSender;
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

    private void assembleFrame() {
        jDialog.setTitle("Leaderboard");
        jDialog.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;
        jDialog.add(leaderboardPanel.getJPanel(), gridBagConstraints);

        okButton.setText("Ok");
        okButton.setFocusable(false);

        resetResultsButton.setText("Reset results");
        resetResultsButton.setFocusable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(resetResultsButton);
        buttonPanel.add(okButton);
        gridBagConstraints.gridy = 1;
        jDialog.add(buttonPanel, gridBagConstraints);

        jDialog.setResizable(false);
        jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

        log.debug("Leaderboard Frame is assembled");
    }
}
