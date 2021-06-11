package ru.chayka.minesweeper.view.leaderboard;

import ru.chayka.minesweeper.dto.LeaderboardDto;
import ru.chayka.minesweeper.observerInterfaces.observers.view.LeaderboardObserver;
import ru.chayka.minesweeper.view.compositeClasses.ViewComposite;

import javax.swing.*;
import java.awt.*;

public class LeaderboardFrame
        implements LeaderboardObserver {
    private final ViewComposite viewComposite;
    private final JDialog jDialog;

    private final LeaderboardPanel leaderboardPanel;
    private final JFrame mainFrame;

    public LeaderboardFrame(JFrame mainFrame) {
        viewComposite = new ViewComposite(this);
        jDialog = new JDialog();

        this.mainFrame = mainFrame;

        jDialog.setTitle("Leaderboard");
        jDialog.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;

        leaderboardPanel = new LeaderboardPanel();
        jDialog.add(leaderboardPanel.getJPanel(), gridBagConstraints);

        ResetResultsButton resetResultsButton = new ResetResultsButton();
        viewComposite.addViewComponent(resetResultsButton.getViewComponent());

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

    public ViewComposite getViewComponent() {
        return viewComposite;
    }

    @Override
    public void acceptLeaderboardDto(LeaderboardDto leaderboardDto) {
        leaderboardPanel.updateLeaderboard(leaderboardDto.getStrDifficultyModes(), leaderboardDto.getTimes(), leaderboardDto.getLeaderNames());
        if (!jDialog.isVisible()) {
            jDialog.setLocationRelativeTo(mainFrame);
            jDialog.setVisible(true);
        }
    }
}
