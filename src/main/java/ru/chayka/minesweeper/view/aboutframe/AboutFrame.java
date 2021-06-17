package ru.chayka.minesweeper.view.aboutframe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.AboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.AboutDtoEventListener;

import javax.swing.*;
import java.awt.*;

public class AboutFrame
        implements AboutDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(AboutFrame.class.getName());

    private final JDialog jDialog;

    private final JFrame mainFrame;
    private final JLabel aboutLabel;

    public AboutFrame(JFrame mainFrame) {
        jDialog = new JDialog();

        this.mainFrame = mainFrame;

        jDialog.setLayout(new BorderLayout());
        jDialog.setPreferredSize(new Dimension(200, 200));
        jDialog.setLocationRelativeTo(mainFrame);
        jDialog.setResizable(false);

        aboutLabel = new JLabel();
        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aboutLabel.setVerticalAlignment(SwingConstants.CENTER);
        jDialog.add(aboutLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(event -> jDialog.setVisible(false));
        okButton.setFocusable(false);
        JPanel okButtonPanel = new JPanel();
        okButtonPanel.add(okButton);
        jDialog.add(okButtonPanel, BorderLayout.PAGE_END);

        jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    }

    @Override
    public void acceptEvent(AboutDtoEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);

        aboutLabel.setText(event.getText());

        jDialog.pack();
        jDialog.setLocationRelativeTo(mainFrame);
        jDialog.setVisible(true);
    }
}