package ru.chayka.minesweeper.model.leaderboard;

import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.settings.Settings;

public class LeaderEntry {
    private String strDifficultyMode;
    private int time;
    private String leaderName;

    public LeaderEntry() {
    }

    public LeaderEntry(DifficultyMode difficultyMode, int time, String leaderName) {
        this.strDifficultyMode = difficultyMode.toString();
        this.time = time;
        this.leaderName = leaderName;
    }

    public LeaderEntry(DifficultyMode difficultyMode) {
        this(difficultyMode, Settings.getInstance().getGameTimeMax(), Settings.getInstance().getDefaultLeaderName());
    }

    public LeaderEntry(DifficultyMode difficultyMode, int time) {
        this(difficultyMode, time, Settings.getInstance().getDefaultLeaderName());
    }

    public String getStrDifficultyMode() {
        return strDifficultyMode;
    }

    public int getTime() {
        return time;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setStrDifficultyMode(String strDifficultyMode) {
        this.strDifficultyMode = strDifficultyMode;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
