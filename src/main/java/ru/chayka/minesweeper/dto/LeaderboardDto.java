package ru.chayka.minesweeper.dto;

import ru.chayka.minesweeper.model.leaderboard.LeaderEntry;
import ru.chayka.minesweeper.model.leaderboard.Leaderboard;

import java.util.ArrayList;

public class LeaderboardDto {
    private final ArrayList<String> strDifficultyModes;
    private final ArrayList<Integer> times;
    private final ArrayList<String> leaderNames;

    public LeaderboardDto(Leaderboard leaderboard) {
        strDifficultyModes = new ArrayList<>();
        times = new ArrayList<>();
        leaderNames = new ArrayList<>();

        ArrayList<LeaderEntry> leaderboardEntries = leaderboard.getLeaderEntries();

        for (LeaderEntry leaderEntry : leaderboardEntries) {
            strDifficultyModes.add(leaderEntry.getStrDifficultyMode());
            times.add(leaderEntry.getTime());
            leaderNames.add(leaderEntry.getLeaderName());
        }
    }

    public ArrayList<String> getStrDifficultyModes() {
        return strDifficultyModes;
    }

    public ArrayList<Integer> getTimes() {
        return times;
    }

    public ArrayList<String> getLeaderNames() {
        return leaderNames;
    }
}
