package ru.chayka.minesweeper.model.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.model.MvcLeaderboardDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.model.MvcRecordNewLeaderEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcLeaderboardDtoEventSender;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcRecordNewLeaderEventSender;
import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.model.eventsystem.events.GameOverEvent;
import ru.chayka.minesweeper.model.eventsystem.listeners.GameOverEventListener;
import ru.chayka.minesweeper.model.timer.GameTimer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Leaderboard
        implements GameOverEventListener {
    private static final Logger log = LoggerFactory.getLogger(Leaderboard.class.getName());

    private final GameTimer gameTimer;

    private ArrayList<LeaderEntry> leaderEntries;
    private LeaderEntry bufferEntry;

    public static final String XML_FILE_NAME = "./leaderboard.xml";

    private final MvcLeaderboardDtoEventSender mvcLeaderboardDtoEventSender;
    private final MvcRecordNewLeaderEventSender mvcRecordNewLeaderEventSender;

    public Leaderboard(GameTimer gameTimer) {
        this.gameTimer = gameTimer;

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

        mvcLeaderboardDtoEventSender = new MvcLeaderboardDtoEventSender();
        mvcRecordNewLeaderEventSender = new MvcRecordNewLeaderEventSender();
    }

    public ArrayList<LeaderEntry> getLeaderEntries() {
        return leaderEntries;
    }

    public void setLeaderEntries(ArrayList<LeaderEntry> leaderEntries) {
        this.leaderEntries = leaderEntries;
    }

    public MvcLeaderboardDtoEventSender getMvcLeaderboardDtoEventSender() {
        return mvcLeaderboardDtoEventSender;
    }

    public MvcRecordNewLeaderEventSender getMvcRecordNewLeaderEventSender() {
        return mvcRecordNewLeaderEventSender;
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
        mvcLeaderboardDtoEventSender.notifyAllListeners(new MvcLeaderboardDtoEvent(this));
    }

    @Override
    public void acceptEvent(GameOverEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        if (event.isWon()) {
            tryToRecordNewLeader(event.getDifficultyMode());
        }
    }

    private void tryToRecordNewLeader(DifficultyMode difficultyMode) {
        for (LeaderEntry leaderEntry : leaderEntries) {
            if (leaderEntry.getStrDifficultyMode().equals(difficultyMode.toString()) &&
                    leaderEntry.getTime() > gameTimer.getGameTime()) {
                bufferEntry = new LeaderEntry(difficultyMode, gameTimer.getGameTime());
                mvcRecordNewLeaderEventSender.notifyAllListeners(
                        new MvcRecordNewLeaderEvent(difficultyMode.toString()));
            }
        }
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
}
