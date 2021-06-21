package ru.chayka.minesweeper.view.aboutframe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.MvcAboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcAboutDtoEventListener;

import javax.swing.*;
import java.awt.*;

public class AboutFrame
        implements MvcAboutDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(AboutFrame.class.getName());

    private final JDialog jDialog;

    private final JFrame mainFrame;
    private final JLabel aboutLabel;
    private final JButton okButton;

    public AboutFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        jDialog = new JDialog();
        aboutLabel = new JLabel();
        okButton = new JButton();

        okButton.addActionListener(event -> jDialog.setVisible(false));

        assembleFrame();
    }

    @Override
    public void acceptEvent(MvcAboutDtoEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);

        aboutLabel.setText(event.getText());

        jDialog.pack();
        jDialog.setLocationRelativeTo(mainFrame);
        jDialog.setVisible(true);
    }

    private void assembleFrame() {
        jDialog.setLayout(new BorderLayout());
        jDialog.setPreferredSize(new Dimension(200, 200));
        jDialog.setLocationRelativeTo(mainFrame);
        jDialog.setResizable(false);

        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aboutLabel.setVerticalAlignment(SwingConstants.CENTER);
        jDialog.add(aboutLabel, BorderLayout.CENTER);

        okButton.setText("Ok");
        okButton.setFocusable(false);
        JPanel okButtonPanel = new JPanel();
        okButtonPanel.add(okButton);
        jDialog.add(okButtonPanel, BorderLayout.PAGE_END);

        jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

        log.debug("About Frame is assembled");
    }
}