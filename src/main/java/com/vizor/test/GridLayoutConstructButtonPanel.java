package com.vizor.test;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;


public class GridLayoutConstructButtonPanel {
    private int indexPge = 1;

    public void setIndexPge(int indexPge) {
        this.indexPge = indexPge;
    }


    public void galleryButtonConstruct(JFrame frame) {

        JPanel galleryPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(ConstantMy.ROWS.getConstant(), ConstantMy.COLUMNS.getConstant(),
                ConstantMy.GAPS.getConstant(), ConstantMy.GAPS.getConstant());
        galleryPanel.setLayout(gridLayout);
        Pagination paginationGallery = new Pagination(indexPge);
        for (Path path : paginationGallery.getPageList()) {
            JButton button = new JButton();
            button.setIcon(paginationGallery.putImage(path));
            button.setName(path.toString());
            button.setContentAreaFilled(false);
            button.addActionListener(e->{
                String pathSt = button.getName();
                ImageIcon icon = new ImageIcon(pathSt);
                JLabel jLabel = new JLabel(icon);
                JFrame frameNew = new JFrame(path.getFileName().toString());
                frameNew.add(jLabel);
                frameNew.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frameNew.setVisible(true);
                frameNew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            });
            galleryPanel.add(button);
        }
        frame.getContentPane().add(galleryPanel);
    }

    public void buttonsPanelConstruct(JFrame frame) {
        JPanel buttonPanel = new JPanel();

        for (int i = 0; i < new Pagination().countTotalPage(); i++) {
            int numPage = i + 1;
            JButton button = new JButton(Integer.toString(numPage));
            buttonPanel.add(button);
            button.addActionListener(e -> {
                setIndexPge(Integer.parseInt(e.getActionCommand()));
                frame.getContentPane().remove(1);
                galleryButtonConstruct(frame);
                frame.setVisible(true);
            });
        }
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}
