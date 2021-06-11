package ru.chayka.minesweeper.view.leaderboard;

import javax.swing.*;
import java.util.Random;

public class NewRecordLabel {
    private final JLabel jLabel;

    private final String[] messages;

    public NewRecordLabel() {
        jLabel = new JLabel();

        messages = new String[5];
        messages[0] = "<html><p align=\"center\">Congrats!</p><br>" +
                "<p align=\"center\">You set a new record on %s difficulty</p><br>" +
                "<p align=\"center\">Enter your name:</p><br></html>";
        messages[1] = "<html><p align=\"left\">oh...</p><br>" +
                "<p align=\"left\">you won... I guess...</p><br>" +
                "<p align=\"left\">enter your name... I guess...</p><br></html>";
        messages[2] = "<html><p align=\"center\">OH MY GOD!!!!!</p><br>" +
                "<p align=\"center\">U WON!!!!!!!!!!!!111111</p><br>" +
                "<p align=\"center\">QUICK ENTER UR NAME!!!!!!!!!!!11!!</p><br></html>";
        messages[3] = "<html><p align=\"left\">Oh, did you just won on %s difficulty?</p><br>" +
                "<p align=\"left\">Well done! I'm so proud of you!:)</p><br>" +
                "<p align=\"left\">Enter your name so everyone knows how smart you are.</p><br></html>";
        messages[4] = "<html><p align=\"left\">Pffft</p><br>" +
                "<p align=\"left\">%s difficulty? Really?</p><br>" +
                "<p align=\"left\">Did you expect a medal or something? Pathetic.</p><br>" +
                "<p align=\"left\">Nevermind. Enter your stupid name:</p><br></html>";
    }

    public JLabel getJLabel() {
        return jLabel;
    }

    public void formMessage(String strDifficultyMode) {
        jLabel.setText(String.format(messages[new Random().nextInt(messages.length)],
                strDifficultyMode));
    }
}
