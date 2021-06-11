package ru.chayka.minesweeper.view;

import ru.chayka.minesweeper.view.aboutFrame.AboutFrame;
import ru.chayka.minesweeper.view.compositeClasses.ViewComposite;
import ru.chayka.minesweeper.view.leaderboard.LeaderboardFrame;
import ru.chayka.minesweeper.view.leaderboard.RecordNewLeaderFrame;
import ru.chayka.minesweeper.view.minesweeperFrame.MinesweeperFrame;

public class MinesweeperView {
    private final ViewComposite viewComposite;
    private final MinesweeperFrame mainFrame;

    public MinesweeperView() {
        viewComposite = new ViewComposite(this);
        mainFrame = new MinesweeperFrame();
        viewComposite.addViewComponent(mainFrame.getViewComponent());

        LeaderboardFrame leaderboardFrame = new LeaderboardFrame(mainFrame.getJFrame());
        viewComposite.addViewComponent(leaderboardFrame.getViewComponent());

        RecordNewLeaderFrame recordNewLeaderFrame = new RecordNewLeaderFrame(mainFrame.getJFrame());
        viewComposite.addViewComponent(recordNewLeaderFrame.getViewComponent());

        AboutFrame aboutFrame = new AboutFrame(mainFrame.getJFrame());
        viewComposite.addViewComponent(aboutFrame.getViewComponent());
    }

    public void showMainFrame() {
        mainFrame.getJFrame().setVisible(true);
    }

    public ViewComposite getViewComponent() {
        return viewComposite;
    }
}