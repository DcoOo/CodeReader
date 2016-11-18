package tech.coordinates.codereader.structure;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import tech.coordinates.codereader.utility.RegexString;

/**
 * Created by Administrator on 2016/11/14.
 */
public class HighLightUtil {

    private static LinkedList<SyntaxNode> syntax_list = new TreeCreater().createSyntaxTree();

    public static void initFileStructure(FileStructure structure){
        for (Row row : structure.getRow_array_list()) {
            if (row.getRowProperty() != RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT && row.getRowProperty() != RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT && !row.toString().equals("")) {
//                Log.d("debug", row.toString());
//                try {
                if (row.toString().matches("\\s*(private|public) static final.*")) {
                    nullPointerExceptionHandler(row);
                } else {
                    handleRow(row);
                }
            }

        }
    }
    private HighLightUtil(FileStructure structure, View view) {
    }

    /**
     * 处理某种模式的异常
     * 类似于public static final int NUMBER = 1;
     * 在此情况下由于处理 类名 和变量名关系的时候无法根据字符串格式区别
     */
    private static void nullPointerExceptionHandler(Row row) {
        LinkedList<Word> list_word = row.getList_words();
        list_word.get(0).setProperty(WordProperty.KEY_WORD_PACKAGE_CONTROL);
        list_word.get(1).setProperty(WordProperty.KEY_WORD_MODIFIER);
        list_word.get(2).setProperty(WordProperty.KEY_WORD_MODIFIER);
        list_word.get(3).setProperty(WordProperty.TYPE_NAME);
        list_word.get(4).setProperty(WordProperty.CUSTOM_VARIABLE_NAME);
    }

    private static void handleRow(Row row) {
        LinkedList<Word> words = row.getList_words();
//        if (row.getList_words().size() >= 1) {
        SyntaxNode next_syntax_node = null;
        for (int i = 0; i < words.size(); i++) {
            Word nextWord;
            if (i == words.size() - 1) {
                nextWord = null;
            } else {
                nextWord = words.get(i + 1);
            }
            if (i == 0)
                next_syntax_node = handleWord(words.get(i), syntax_list, nextWord);
            else
                next_syntax_node = handleWord(words.get(i), next_syntax_node.getChildNode() == null ? null : next_syntax_node.getChildNode(), nextWord);


        }

//        }
    }

    private static SyntaxNode handleWord(Word word, LinkedList<SyntaxNode> node_list, Word next_word) {
        //当语法节点为空，说明到头
        //处理注解
        if (word.toString().matches(RegexString.JAVA_EXPLAIN_NOTA)) {
            word.setProperty(WordProperty.EXPLAIN);
            return null;
        }
        if (node_list == null) {
            return null;
        }
        if (node_list.size() == 1) {
            word.setProperty(node_list.get(0).getTag());
            return node_list.get(0);
        }
        for (SyntaxNode node : node_list) {
            if (word.toString().equals(node.getName())) {
                word.setProperty(node.getTag());
                return node;
            }
        }
        return setWord(word, node_list, next_word);
    }

    private static SyntaxNode setWord(Word word, LinkedList<SyntaxNode> node_list, Word next_word) {
        for (SyntaxNode node : node_list) {
            //根据具体情况来分析
            switch (node.getTag()) {
                case WordProperty.TYPE_NAME:
                    //判断标准过于牵强，记录修改
                    if ((word.toString().charAt(0) + "").matches("[A-Z]")) {
                        word.setProperty(node.getTag());
                        return node;
                    }
                    break;
                case WordProperty.IMPORT_PACKAGE_NAME:
                    if (word.toString().matches("^[a-z|0-9]*$")) {
                        word.setProperty(node.getTag());
                        return node;
                    }
                    break;
                case WordProperty.CUSTOM_VARIABLE_NAME:

                    if (!word.toString().contains("(") && !word.toString().equals("extends") && !word.toString().equals("implements")
                            && !(word.toString().charAt(0) + "").matches("[A-Z]")
                            && !word.toString().equals("{")) {
                        word.setProperty(node.getTag());
                        return node;
                    }
                    break;
                case WordProperty.CUSTOM_FUNCTION_NAME:
                    if (word.toString().contains("(")) {
                        word.setProperty(node.getTag());
                        return node;
                    }
                    break;
                case WordProperty.INTERFACE_NAME:
                    word.setProperty(node.getTag());
                    return node;
                default:
            }
        }
        return null;
    }


    public static void highLight(FileStructure structure){
        ArrayList<Row> list_rows = structure.getRow_array_list();
        //行号
        //对每一行的词，将一个词放入Map中，包括词在原行中的起始位置和结束位置、单词属性
        for (Row r : list_rows){
            rowHandle(r);
        }
    }

    public static void rowHandle(Row row){
//        String target = row.toString();
        LinkedList<Word> list_words = row.getList_words();
        LinkedList<HashMap<String,Integer>> words_highlight = row.getWords_info();
        if (row.getRowProperty() != RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT && row.getRowProperty() != RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT)
        for (Word w : list_words){
            try {
                wordHandle(w,words_highlight,row);
            }
            catch (StringIndexOutOfBoundsException e){
                wordHandle(w,words_highlight,row);
            }
        }

    }
    private static HashMap<String,Integer> item;
    public static void wordHandle(Word word,LinkedList<HashMap<String,Integer>> words_highlight,Row row){
        item = new HashMap<>();
        String row_cont = row.toString();
        String word_cont = word.toString();
        int start = row_cont.indexOf(word_cont);
        int end = start + word_cont.length();
        item.put("start",start);
        item.put("end",end);
        item.put("property",word.getProperty());
        words_highlight.add(item);
//        Log.d("debug",row_cont.substring(start,end)+"|"+word.getProperty());
    }

    public static void setHighLigntSpanStr4Row(Row row){
        SpannableString span_str = new SpannableString(row.toString()+"\n");
        if (row.toString().equals("")){
//            span_str = new SpannableString("\n");
            row.setSpan_str(span_str);
        }
        else if (row.getRowProperty() == RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT || row.getRowProperty() == RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT){
            ForegroundColorSpan comment_span = new ForegroundColorSpan(ColorControler.color_wordproperty.get(row.getRowProperty()));
//            span_str = new SpannableString(row.toString()+"\n");
            span_str.setSpan(comment_span,0,row.toString().length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        else
            for (HashMap<String,Integer> item : row.getWords_info()){
            ForegroundColorSpan color_span;
            if (item.get("property") == null){
                color_span = new ForegroundColorSpan(ColorControler.color_wordproperty.get("COLOR_UNKNOW"));
            }else {
                color_span = new ForegroundColorSpan(ColorControler.color_wordproperty.get(item.get("property")));
            }
            span_str.setSpan(color_span,item.get("start"),item.get("end"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        row.setSpan_str(span_str);
    }
}
