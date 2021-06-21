package ru.chayka.minesweeper.view.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.MvcRecordNewLeaderEvent;
import ru.chayka.minesweeper.eventsystem.events.view.MvcNewLeaderDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcRecordNewLeaderEventListener;
import ru.chayka.minesweeper.eventsystem.senders.view.MvcNewLeaderDtoEventSender;

import javax.swing.*;
import java.awt.*;

public class RecordNewLeaderFrame
        implements MvcRecordNewLeaderEventListener {
    private static final Logger log = LoggerFactory.getLogger(RecordNewLeaderFrame.class.getName());

    private final JDialog jDialog;

    private final JFrame mainFrame;
    private final NewRecordLabel newRecordLabel;
    private final JTextField textField;
    private final JButton okButton;

    private final String defaultLeaderName = "Anon";

    private final MvcNewLeaderDtoEventSender mvcNewLeaderDtoEventSender;

    public RecordNewLeaderFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        jDialog = new JDialog();
        newRecordLabel = new NewRecordLabel();
        textField = new JTextField();
        okButton = new JButton();

        mvcNewLeaderDtoEventSender = new MvcNewLeaderDtoEventSender();

        okButton.addActionListener(event -> {
            if (!textField.getText().isBlank()) {
                mvcNewLeaderDtoEventSender.notifyAllListeners(
                        new MvcNewLeaderDtoEvent(textField.getText()));
            }
        });
        okButton.addActionListener(event -> jDialog.setVisible(false));

        assembleFrame();
    }

    public MvcNewLeaderDtoEventSender getMvcNewLeaderDtoEventSender() {
        return mvcNewLeaderDtoEventSender;
    }

    @Override
    public void acceptEvent(MvcRecordNewLeaderEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);

        newRecordLabel.formMessage(event.getDifficulty());

        jDialog.setLocationRelativeTo(mainFrame);
        jDialog.pack();
        jDialog.setVisible(true);
    }

    private void assembleFrame() {
        jDialog.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridy = 0;
        JPanel labelPanel = new JPanel();
        labelPanel.add(newRecordLabel.getJLabel());
        jDialog.add(labelPanel, gridBagConstraints);

        textField.setText(defaultLeaderName);
        gridBagConstraints.gridy = 1;
        textField.setPreferredSize(new Dimension(150, 18));
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(textField);
        jDialog.add(textFieldPanel, gridBagConstraints);

        okButton.setText("Ok");
        okButton.setFocusable(false);
        gridBagConstraints.gridy = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        jDialog.add(buttonPanel, gridBagConstraints);

        jDialog.setResizable(false);
        jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

        log.debug("Record New Leader Frame is assembled");
    }
}
