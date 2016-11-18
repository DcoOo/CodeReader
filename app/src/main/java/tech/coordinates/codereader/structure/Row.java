package tech.coordinates.codereader.structure;

import android.text.SpannableString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tech.coordinates.codereader.utility.ContentUtility;

/**
 * Created by Administrator on 2016/11/13.
 */
public class Row {

    private LinkedList<Character> list_row_content;
    private LinkedList<Word> list_words;
    private int rowProperty = RowProperty.ROW_PROPERTY_COMMON;
    private LinkedList<HashMap<String,Integer>> words_info;
    private String row_cont;

    public SpannableString getSpan_str() {
        return span_str;
    }

    public void setSpan_str(SpannableString span_str) {

        this.span_str = span_str;
    }

    private SpannableString span_str;

    public void setWords_info(LinkedList<HashMap<String, Integer>> words_info) {
        this.words_info = words_info;
    }

    public void setRow_cont(String row_cont) {
        this.row_cont = row_cont;
    }


    public String getRow_cont() {
        return row_cont;
    }

    public LinkedList<HashMap<String, Integer>> getWords_info() {
        return words_info;
    }

    public Row(String row_content) {
        list_row_content = new LinkedList<>();
        this.row_cont = row_content;
        for (byte b : row_content.getBytes()) {
            list_row_content.add((char) b);
        }
        ContentUtility.setRowProperty(this);
        ContentUtility.spliteWords(this);

        words_info = new LinkedList<>();

    }

    public void setList_row_content(LinkedList<Character> list_row_content) {
        this.list_row_content = list_row_content;
    }

    public void setList_words(LinkedList<Word> list_words) {
        this.list_words = list_words;
    }

    public LinkedList<Character> getList_row_content() {

        return list_row_content;
    }

    public LinkedList<Word> getList_words() {
        return list_words;
    }

    public void setRowProperty(int rowProperty) {
        this.rowProperty = rowProperty;
    }

    public int getRowProperty() {

        return rowProperty;
    }

    //    private SyntaxNode position

    @Override
    public String toString() {
        String content = "";
        for (Character c : list_row_content) {
            content += c + "";
        }
        return content;
    }

}
