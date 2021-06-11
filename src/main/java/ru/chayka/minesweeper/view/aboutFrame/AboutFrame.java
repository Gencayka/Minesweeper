package ru.chayka.minesweeper.view.aboutFrame;

import ru.chayka.minesweeper.observerInterfaces.observers.view.AboutMinesweeperObserver;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.awt.*;

public class AboutFrame
        implements AboutMinesweeperObserver {
    private final ViewItem viewItem;
    private final JDialog jDialog;

    private final JFrame mainFrame;
    private final JLabel aboutLabel;

    public AboutFrame(JFrame mainFrame) {
        viewItem = new ViewItem(this);
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

    public ViewItem getViewComponent() {
        return viewItem;
    }

    @Override
    public void acceptAboutData(String text) {
        aboutLabel.setText(text);

        jDialog.pack();
        jDialog.setLocationRelativeTo(mainFrame);
        jDialog.setVisible(true);
    }
}