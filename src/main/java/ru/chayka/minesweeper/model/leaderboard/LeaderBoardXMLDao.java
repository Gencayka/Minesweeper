package ru.chayka.minesweeper.model.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.model.settings.Settings;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LeaderBoardXMLDao {
    private static final Logger log = LoggerFactory.getLogger(LeaderBoardXMLDao.class.getName());

    public static void serializeToXML(Leaderboard leaderboard) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(Settings.getInstance().getLeaderboardXmlFileName())))) {
            encoder.writeObject(leaderboard.getLeaderEntries());
        } catch (IOException io) {
            log.error("Failed to open file");
            log.error(io.getMessage(), io);
        }
    }

    public static void deserializeFromXML(Leaderboard leaderboard) throws IOException {
        try (XMLDecoder decoder = new XMLDecoder(
                new FileInputStream(Settings.getInstance().getLeaderboardXmlFileName()))) {
            leaderboard.setLeaderEntries((ArrayList<LeaderEntry>) decoder.readObject());
        } catch (IOException io) {
            log.error("Failed to open file");
            log.error(io.getMessage(), io);
            throw io;
        }
    }
}
