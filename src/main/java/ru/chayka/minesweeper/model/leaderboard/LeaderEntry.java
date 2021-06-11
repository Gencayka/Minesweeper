package ru.chayka.minesweeper.model.leaderboard;

import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.timer.GameTimer;

public class LeaderEntry {
    private final static int DEFAULT_TIME = GameTimer.GAME_TIME_MAX;
    private final static String DEFAULT_LEADER_NAME = "Leeroy Jenkins";

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
        this(difficultyMode, DEFAULT_TIME, DEFAULT_LEADER_NAME);
    }

    public LeaderEntry(DifficultyMode difficultyMode, int time) {
        this(difficultyMode, time, DEFAULT_LEADER_NAME);
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
