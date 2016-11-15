package tech.coordinates.codereader.utility;

import java.util.LinkedList;

import tech.coordinates.codereader.structure.Row;
import tech.coordinates.codereader.structure.RowProperty;
import tech.coordinates.codereader.structure.Word;
import tech.coordinates.codereader.structure.WordProperty;

/**
 * Created by Administrator on 2016/11/13.
 */
public class ContentUtility {

    public static int getProperty(Word word){
        String word_str = word.toString();
        if (word_str.matches(RegexString.JAVA_ACCESS_CONTROL)){
            return WordProperty.KEY_WORD_PACKAGE_CONTROL;
        }
        if (word_str.matches(RegexString.JAVA_BASIC_TYPE)){
            return WordProperty.KEY_WORD_BASIC_TYPE;
        }
        if (word_str.matches(RegexString.JAVA_EXCEPTION)){
            return WordProperty.KEY_WORD_ERROR_HANDLE;
        }
        if (word_str.matches(RegexString.JAVA_MODIFIER)){
            return WordProperty.KEY_WORD_MODIFIER;
        }
        if (word_str.matches(RegexString.JAVA_REMAIN_WORDS)){
            return WordProperty.KEY_WORD_RETAINS;
        }
        if (word_str.matches(RegexString.JAVA_ROUTINE_CONTROL)){
            return WordProperty.KEY_WORD_ROUTINE_CONTROL;
        }
        if (word_str.matches(RegexString.JAVA_VARIABLE_LEAD)){
            return WordProperty.KEY_WORD_VARIABLE_LINK;
        }
        if (word_str.matches(RegexString.JAVA_PACKAGE)){
            return WordProperty.KEY_WORD_PACKAGE;
        }
        //判断自定义变量
        return 000;
        //判断自定义类名
        //判断自定义方法名
    }

    public static LinkedList<Word> spliteWords(Row row) {
        if (row.getRowProperty() != RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT && row.getRowProperty() != RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT){
            LinkedList<Word> words = new LinkedList<>();
            String target_str = row.toString().trim();
            int count = 0;
            for (int i = 0; i < target_str.length(); i++) {
                if (target_str.charAt(i) == ' ') {
                    count++;
                }
                if (target_str.charAt(i) == '(') {
                    break;
                }
            }
            String[] result = target_str.split(" ", ++count);
            for (String s : result) {
//            System.out.println(s);
                words.add(new Word(s));
            }
            row.setList_words(words);
            return words;
        }
        return null;
    }
    public static void setRowProperty(Row row){
        String row_cont = row.toString().trim();
        if (row_cont.length() > 1){
            if (row_cont.matches(RegexString.COMMON_COMMENT2) || row_cont.matches(RegexString.COMMON_COMMENT3) || row_cont.matches(RegexString.COMMON_COMMENT4)){
                row.setRowProperty(RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT);
            }
            if (row_cont.matches(RegexString.COMMON_COMMENT1)){
                row.setRowProperty(RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT);
            }
        }

    }
    public static void main(String[] args){
        String row = "public static LinkedList<Word> spliteWords(Row row){";
        int count = 0;
        for (int i = 0;i < row.length();i++){
            if (row.charAt(i) == ' '){
                count++;
            }
            if (row.charAt(i) == '('){
                break;
            }
        }
        String[] result = row.split(" ",++count);
        for (String s : result){
//            System.out.println(s);

        }
    }
}
