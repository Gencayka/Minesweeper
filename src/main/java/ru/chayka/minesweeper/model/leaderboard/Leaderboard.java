package ru.chayka.minesweeper.model.leaderboard;

import ru.chayka.minesweeper.eventsystem.events.model.LeaderboardDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.model.RecordNewLeaderEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.LeaderboardDtoEventSender;
import ru.chayka.minesweeper.eventsystem.senders.model.RecordNewLeaderEventSender;
import ru.chayka.minesweeper.model.DifficultyMode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<LeaderEntry> leaderEntries;
    private LeaderEntry bufferEntry;

    public static final String XML_FILE_NAME = "./leaderboard.xml";

    private final LeaderboardDtoEventSender leaderboardDtoEventSender;
    private final RecordNewLeaderEventSender recordNewLeaderEventSender;

    public Leaderboard() {
        leaderEntries = new ArrayList<>();

        bufferEntry = new LeaderEntry();

        if (Files.exists(Paths.get(XML_FILE_NAME))) {
            try {
                LeaderBoardXMLDao.deserializeFromXML(this);
                if (!checkLeaderboardFileConformity()) {
                    createNewLeaderboard();
                }
            } catch (IOException io) {
                createNewLeaderboard();
            }
        } else {
            createNewLeaderboard();
        }

        leaderboardDtoEventSender = new LeaderboardDtoEventSender();
        recordNewLeaderEventSender = new RecordNewLeaderEventSender();
    }

    public ArrayList<LeaderEntry> getLeaderEntries() {
        return leaderEntries;
    }

    public void setLeaderEntries(ArrayList<LeaderEntry> leaderEntries) {
        this.leaderEntries = leaderEntries;
    }

    public LeaderboardDtoEventSender getLeaderboardDtoEventSender() {
        return leaderboardDtoEventSender;
    }

    public RecordNewLeaderEventSender getRecordNewLeaderEventSender() {
        return recordNewLeaderEventSender;
    }

    public void createNewLeaderboard() {
        leaderEntries = new ArrayList<>();
        for (DifficultyMode difficultyMode : DifficultyMode.values()) {
            leaderEntries.add(new LeaderEntry(difficultyMode));
        }
        LeaderBoardXMLDao.serializeToXML(this);
    }

    public void resetLeaderboard() {
        createNewLeaderboard();
        sendLeaderboardEntriesToView();
    }

    private boolean checkLeaderboardFileConformity() {
        if (DifficultyMode.values().length == leaderEntries.size()) {
            for (int i = 0; i < leaderEntries.size(); i++) {
                if (!leaderEntries.get(i).getStrDifficultyMode().equals(
                        DifficultyMode.values()[i].toString())) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void tryToRecordNewLeader(DifficultyMode difficultyMode, int time) {
        for (LeaderEntry leaderEntry : leaderEntries) {
            if (leaderEntry.getStrDifficultyMode().equals(difficultyMode.toString()) &&
                    leaderEntry.getTime() > time) {
                bufferEntry = new LeaderEntry(difficultyMode, time);
                recordNewLeaderEventSender.notifyAllListeners(
                        new RecordNewLeaderEvent(difficultyMode.toString()));
            }
        }
    }

    public void recordNewLeader(String leaderName) {
        for (LeaderEntry leaderEntry : leaderEntries) {
            if (bufferEntry.getStrDifficultyMode().equals(leaderEntry.getStrDifficultyMode())) {
                leaderEntry.setTime(bufferEntry.getTime());
                leaderEntry.setLeaderName(leaderName);
            }
        }
        LeaderBoardXMLDao.serializeToXML(this);
        sendLeaderboardEntriesToView();
    }

    public void sendLeaderboardEntriesToView() {
        leaderboardDtoEventSender.notifyAllListeners(new LeaderboardDtoEvent(this));
    }
}
