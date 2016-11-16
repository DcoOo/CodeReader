package tech.coordinates.codereader.structure;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 定义文章如何显示，每一行是一个LinkedList
 * 总体是一个ArrayList
 */

public class FileStructure {

    private ArrayList<Row> row_array_list = new ArrayList<>();
    private LinkedList<String> row_link_list;

    public FileStructure() {

    }

    public FileStructure(LinkedList<String> row_link_list) {
        this.row_link_list = row_link_list;
        init();
    }

    private void init() {
        Row row;
        for (String s : row_link_list) {
            row = new Row(s);
            row_array_list.add(row);
        }
    }

    public ArrayList<Row> getRow_array_list() {
        return row_array_list;
    }
}
