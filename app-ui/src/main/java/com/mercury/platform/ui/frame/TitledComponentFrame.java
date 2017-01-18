package com.mercury.platform.ui.frame;

import com.mercury.platform.ui.misc.AppThemeColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Константин on 18.01.2017.
 */
public abstract class TitledComponentFrame extends ComponentFrame {
    protected JPanel miscPanel;
    protected TitledComponentFrame(String title) {
        super(title);
        miscPanel = new JPanel();
    }

    @Override
    protected void initialize() {
        super.initialize();
        initHeaderPanel();
    }

    private void initHeaderPanel(){
        if(layout instanceof BorderLayout) {
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(AppThemeColor.TRANSPARENT);
            headerPanel.setBorder(BorderFactory.createEmptyBorder(-6, 0, -6, 0));

            JLabel frameTitleLabel = componentsFactory.getTextLabel(getFrameTitle());
            frameTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            frameTitleLabel.addMouseListener(new DraggedFrameMouseListener());
            frameTitleLabel.addMouseMotionListener(new DraggedFrameMotionListener());

            headerPanel.add(frameTitleLabel, BorderLayout.CENTER);

            miscPanel.setBackground(AppThemeColor.TRANSPARENT);
            JButton hideButton = componentsFactory.getIconButton("app/close.png", 12, AppThemeColor.FRAME_1);
            hideButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    TitledComponentFrame.this.setVisible(false);
                }
            });
            miscPanel.add(hideButton);
            headerPanel.add(miscPanel, BorderLayout.LINE_END);
            this.add(headerPanel, BorderLayout.PAGE_START);
        }
    }
    protected abstract String getFrameTitle();
}