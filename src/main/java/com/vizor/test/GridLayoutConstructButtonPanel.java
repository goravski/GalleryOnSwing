package com.vizor.test;

import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class GridLayoutConstructButtonPanel {
    private int indexPge = 1;

    public void setIndexPge(int indexPge) {
        this.indexPge = indexPge;
    }

    public void textFieldPanelConstruct(JFrame frame) {

        List<Path> pathList = Pagination.fulPathLList;
        List<String> fileName = pathList.stream()
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());
        JPanel searchPanel = new JPanel();
        JComboBox comboBox = new JComboBox(fileName.toArray());
        comboBox.setEditable(true);
        comboBox.setAlignmentY(Component.LEFT_ALIGNMENT);
        comboBox.addActionListener(e -> {
            String item = (String) comboBox.getSelectedItem();
            getRealSizeImage(Pagination.mapPath.get(item));
        });
        searchPanel.add(comboBox);
        frame.getContentPane().add(searchPanel, BorderLayout.NORTH);
        frame.setVisible(true);
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
            button.addActionListener(e -> {
                String pathSt = button.getName();
                Path buttonPath = Paths.get(pathSt);
                getRealSizeImage(buttonPath);
            });
            galleryPanel.add(button);
        }
        frame.getContentPane().add(galleryPanel, BorderLayout.CENTER);
    }

    public void buttonsPanelConstruct(JFrame frame) {
        JPanel buttonPanel = new JPanel();

        for (int i = 0; i < new Pagination().countTotalPage(); i++) {
            int numPage = i + 1;
            JButton button = new JButton(Integer.toString(numPage));
            buttonPanel.add(button);
            button.addActionListener(e -> {
                setIndexPge(Integer.parseInt(e.getActionCommand()));
                frame.getContentPane().remove(2);
                galleryButtonConstruct(frame);
                frame.setVisible(true);
            });
        }
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void getRealSizeImage(Path path) {
        ImageIcon icon = new ImageIcon(path.toString());
        JLabel jLabel = new JLabel(icon);
        JFrame frameNew = new JFrame(path.getFileName().toString());
        frameNew.add(jLabel);
        frameNew.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameNew.setVisible(true);
        frameNew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
