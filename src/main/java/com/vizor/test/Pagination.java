package com.vizor.test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Pagination {
    private final int maxIconPerPage;
    private int currentPage;
    public static final List<Path> fulPathLList = getPathList();
    private static final int sizeList = fulPathLList.size();
    public static final Map<String, Path> mapPath = new HashMap<>(getMapPath());


    public Pagination(int currentPage) {
        this();
        this.currentPage = currentPage;
    }

    public Pagination() {
        int maxRows = ConstantMy.ROWS.getConstant();
        int maxColumns = ConstantMy.COLUMNS.getConstant();
        maxIconPerPage = maxColumns * maxRows;
    }

    private static Map<String, Path> getMapPath() {
        return fulPathLList.stream()
                .collect(Collectors.toMap(path -> path.getFileName().toString(), path -> path));
    }

    private static List<Path> getPathList() {
        List<Path> pathList = new CopyOnWriteArrayList<>();
        Path path = FileSystems.getDefault().getPath("assets\\").toAbsolutePath();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            directoryStream.forEach(pathList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathList;
    }

    public List<Path> getPageList() {
        int offset = (currentPage - 1) * maxIconPerPage;
        int onset;
        if (currentPage < countTotalPage()) {
            onset = currentPage * maxIconPerPage;
        } else {
            onset = sizeList;
        }
        return fulPathLList.subList(offset, onset);
    }

    public int countTotalPage() {
        int totalPage = sizeList / maxIconPerPage;
        if (sizeList % maxIconPerPage > 0) {
            totalPage += 1;
        }
        return totalPage;
    }

    public ImageIcon putImage(Path path) {
        ImageIcon icon = new ImageIcon(path.toString());
        double index = resizeHeight(icon);
        int width = ConstantMy.SCALE_WIDTH.getConstant();
        double heightDouble = (width * index);
        int height = (int) Math.round(heightDouble);
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    private double resizeHeight(ImageIcon icon) {
        double height = icon.getIconHeight();
        double width = icon.getIconWidth();
        return height / width;
    }
}
