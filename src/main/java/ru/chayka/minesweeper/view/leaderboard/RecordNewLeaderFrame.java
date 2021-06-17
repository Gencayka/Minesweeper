package ru.chayka.minesweeper.view.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.RecordNewLeaderEvent;
import ru.chayka.minesweeper.eventsystem.events.view.NewLeaderDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.RecordNewLeaderEventListener;
import ru.chayka.minesweeper.eventsystem.senders.view.NewLeaderDtoEventSender;

import javax.swing.*;
import java.awt.*;

public class RecordNewLeaderFrame
        implements RecordNewLeaderEventListener {
    private static final Logger log = LoggerFactory.getLogger(RecordNewLeaderFrame.class.getName());

    private final JDialog jDialog;

    private final JFrame mainFrame;
    private final NewRecordLabel message;
    private final JTextField textField;

    private final String defaultLeaderName = "Anon";

    private final NewLeaderDtoEventSender newLeaderDtoEventSender;

    public RecordNewLeaderFrame(JFrame mainFrame) {
        jDialog = new JDialog();

        newLeaderDtoEventSender = new NewLeaderDtoEventSender();

        this.mainFrame = mainFrame;

        jDialog.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridy = 0;
        message = new NewRecordLabel();
        JPanel labelPanel = new JPanel();
        labelPanel.add(message.getJLabel());
        jDialog.add(labelPanel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        textField = new JTextField(defaultLeaderName);
        textField.setPreferredSize(new Dimension(150, 18));
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(textField);
        jDialog.add(textFieldPanel, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(event -> {
            if (!textField.getText().isBlank()) {
                newLeaderDtoEventSender.notifyAllListeners(
                        new NewLeaderDtoEvent(textField.getText()));
            }
        });
        okButton.addActionListener(event -> jDialog.setVisible(false));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        jDialog.add(buttonPanel, gridBagConstraints);

        jDialog.setResizable(false);
        jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    }

    public NewLeaderDtoEventSender getNewLeaderDtoEventSender() {
        return newLeaderDtoEventSender;
    }

    @Override
    public void acceptEvent(RecordNewLeaderEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);

        message.formMessage(event.getDifficulty());

        jDialog.setLocationRelativeTo(mainFrame);
        jDialog.pack();
        jDialog.setVisible(true);
    }
}
