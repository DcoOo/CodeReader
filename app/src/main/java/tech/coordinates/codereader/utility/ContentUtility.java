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

    public static int getProperty(Word word) {
        String word_str = word.toString();
        if (word_str.matches(RegexString.JAVA_ACCESS_CONTROL)) {
            return WordProperty.KEY_WORD_PACKAGE_CONTROL;
        }
        if (word_str.matches(RegexString.JAVA_BASIC_TYPE)) {
            return WordProperty.KEY_WORD_BASIC_TYPE;
        }
        if (word_str.matches(RegexString.JAVA_EXCEPTION)) {
            return WordProperty.KEY_WORD_ERROR_HANDLE;
        }
        if (word_str.matches(RegexString.JAVA_MODIFIER)) {
            return WordProperty.KEY_WORD_MODIFIER;
        }
        if (word_str.matches(RegexString.JAVA_REMAIN_WORDS)) {
            return WordProperty.KEY_WORD_RETAINS;
        }
        if (word_str.matches(RegexString.JAVA_ROUTINE_CONTROL)) {
            return WordProperty.KEY_WORD_ROUTINE_CONTROL;
        }
        if (word_str.matches(RegexString.JAVA_VARIABLE_LEAD)) {
            return WordProperty.KEY_WORD_VARIABLE_LINK;
        }
        if (word_str.matches(RegexString.JAVA_PACKAGE)) {
            return WordProperty.KEY_WORD_PACKAGE;
        }
        //判断自定义变量
        return 000;
        //判断自定义类名
        //判断自定义方法名
    }

    /**
     * 分词处理
     * 1.按照空格分词
     * 2.按照 。 分词
     *
     * @param row
     * @return
     */
    public static LinkedList<Word> spliteWords(Row row) {
        if (row.getRowProperty() != RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT && row.getRowProperty() != RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT) {
            LinkedList<Word> words = new LinkedList<>();
            String target_str = row.toString().trim();
            int count_space = 0;
//            count_space = calculateLimit(' ', target_str, '(');
//            String[] space_result = target_str.split(" ", ++count_space);
            String[] space_result = splitByChar(target_str, ' ');
            String[] point_result = null;
            for (String s : space_result) {
                //按照 。 分词
                if (!s.contains("."))
                    words.add(new Word(s));
                else {
//                    int count_point = 0;
//                    count_point = calculateLimit('.',s,'(');
//                    point_result = s.split("\\.",count_point);
//                    if (point_result != null)
                    for (String s_point : splitByChar(s, '.')) {
                        words.add(new Word(s_point));
                    }
                }
            }
            row.setList_words(words);
            return words;
        }
        return null;
    }

    private static String[] splitByChar(String target_str, char target) {
        String[] splite_point;
        int left = 0;
        int right = 0;
        int count = 0;
        for (int i = 0; i < target_str.length(); i++) {
            if (target_str.charAt(i) == '(') {
                left++;
            }
            if (target_str.charAt(i) == ')') {
                right++;
            }
            if (target_str.charAt(i) == target && right == left) {
                count++;
            }
        }
        splite_point = new String[++count];
        left = 0;
        right = 0;
        int last = 0;
        int index = 0;
        for (int i = 0; i < target_str.length(); i++) {
            if (target_str.charAt(i) == '(') {
                left++;
            }
            if (target_str.charAt(i) == ')') {
                right++;
            }
            if (target_str.charAt(i) == target && right == left) {
                splite_point[index] = target_str.substring(last, i);
                last = i + 1;
                index++;
            }
        }
        splite_point[index] = target_str.substring(last, target_str.length());
        return splite_point;
    }

    private static int calculateLimit(char target_c, String target_str, char stop_c) {
        int count = 0;
        for (int i = 0; i < target_str.length(); i++) {
            if (target_str.charAt(i) == target_c) {
                count++;
            }
            if (target_str.charAt(i) == stop_c) {
                break;
            }
        }
        return ++count;
    }

    public static void setRowProperty(Row row) {
        String row_cont = row.toString().trim();
        if (row_cont.length() > 1) {
            if (row_cont.matches(RegexString.COMMON_COMMENT2) || row_cont.matches(RegexString.COMMON_COMMENT3) || row_cont.matches(RegexString.COMMON_COMMENT4)) {
                row.setRowProperty(RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT);
            }
            if (row_cont.matches(RegexString.COMMON_COMMENT1)) {
                row.setRowProperty(RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT);
            }
        }

    }

    public static void main(String[] args) {
        String row = "public static LinkedList<Word> spliteWords(Row row){";
        int count = 0;
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == ' ') {
                count++;
            }
            if (row.charAt(i) == '(') {
                break;
            }
        }
        String[] result = row.split(" ", ++count);
        for (String s : result) {
//            System.out.println(s);

        }
    }
}
