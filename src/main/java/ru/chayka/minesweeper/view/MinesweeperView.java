package ru.chayka.minesweeper.view;

import ru.chayka.minesweeper.view.aboutframe.AboutFrame;
import ru.chayka.minesweeper.view.leaderboard.LeaderboardFrame;
import ru.chayka.minesweeper.view.leaderboard.RecordNewLeaderFrame;
import ru.chayka.minesweeper.view.mainframe.MainFrame;

public class MinesweeperView {
    private final MainFrame mainFrame;
    private final LeaderboardFrame leaderboardFrame;
    private final RecordNewLeaderFrame recordNewLeaderFrame;
    private final AboutFrame aboutFrame;

    public MinesweeperView() {
        mainFrame = new MainFrame();
        leaderboardFrame = new LeaderboardFrame(mainFrame.getJFrame());
        recordNewLeaderFrame = new RecordNewLeaderFrame(mainFrame.getJFrame());
        aboutFrame = new AboutFrame(mainFrame.getJFrame());
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public LeaderboardFrame getLeaderboardFrame() {
        return leaderboardFrame;
    }

    public RecordNewLeaderFrame getRecordNewLeaderFrame() {
        return recordNewLeaderFrame;
    }

    public AboutFrame getAboutFrame() {
        return aboutFrame;
    }

    public void showMainFrame() {
        mainFrame.getJFrame().setVisible(true);
    }
}