package ru.chayka.minesweeper.model.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.dto.LeaderboardDto;
import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.observerInterfaces.observables.model.LeaderboardObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.LeaderboardObserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Leaderboard
        implements LeaderboardObservable {
    private static final Logger log = LoggerFactory.getLogger(Leaderboard.class.getName());

    private final ArrayList<LeaderboardObserver> observers;
    private final RecordNewLeaderNotificator recordNewLeaderNotificator;

    private ArrayList<LeaderEntry> leaderEntries;
    private LeaderEntry bufferEntry;

    public static final String XML_FILE_NAME = "./leaderboard.xml";

    public Leaderboard() {
        leaderEntries = new ArrayList<>();

        observers = new ArrayList<>();
        recordNewLeaderNotificator = new RecordNewLeaderNotificator();

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
    }

    public ArrayList<LeaderEntry> getLeaderEntries() {
        return leaderEntries;
    }

    public void setLeaderEntries(ArrayList<LeaderEntry> leaderEntries) {
        this.leaderEntries = leaderEntries;
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
        sendLeaderboardInfoToView();
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
                recordNewLeaderNotificator.notifyObservers(difficultyMode.toString());
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
        sendLeaderboardInfoToView();
    }

    public void sendLeaderboardInfoToView() {
        sendLeaderboardDto(new LeaderboardDto(this));
    }

    public RecordNewLeaderNotificator getRecordNewLeaderNotificator() {
        return recordNewLeaderNotificator;
    }

    @Override
    public void registerObserver(LeaderboardObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(LeaderboardObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void sendLeaderboardDto(LeaderboardDto dto) {
        for (var observer : observers) {
            MVCLogger.logDtoSending(log, this, observer);
            observer.acceptLeaderboardDto(dto);
        }
    }
}
