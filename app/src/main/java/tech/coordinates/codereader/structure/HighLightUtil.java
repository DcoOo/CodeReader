package tech.coordinates.codereader.structure;

import android.util.Log;
import android.view.View;

import java.util.LinkedList;

import tech.coordinates.codereader.utility.RegexString;

/**
 * Created by Administrator on 2016/11/14.
 */
public class HighLightUtil {

    private LinkedList<SyntaxNode> syntax_list = new TreeCreater().createSyntaxTree();

    public HighLightUtil(FileStructure structure, View view) {
        for (Row row : structure.getRow_array_list()) {
            if (row.getRowProperty() != RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT && row.getRowProperty() != RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT && !row.toString().equals("")) {
                Log.d("debug", row.toString());
                try {
                    if (row.toString().matches("\\s*(private|public) static final.*")) {
                        nullPointerExceptionHandler(row);
                    } else {
                        handleRow(row);
                    }
                } catch (NullPointerException e) {
                    Log.d("debug", "NullPointerException : " + row.toString());
                } catch (StringIndexOutOfBoundsException e) {
                    Log.d("debug", "StringIndexOutOfBoundsException : " + row.toString());
                }
            }

        }

    }

    /**
     * 处理某种模式的异常
     * 类似于public static final int NUMBER = 1;
     * 在此情况下由于处理 类名 和变量名关系的时候无法根据字符串格式区别
     */
    private void nullPointerExceptionHandler(Row row) {
        LinkedList<Word> list_word = row.getList_words();
        list_word.get(0).setProperty(WordProperty.KEY_WORD_PACKAGE_CONTROL);
        list_word.get(1).setProperty(WordProperty.KEY_WORD_MODIFIER);
        list_word.get(2).setProperty(WordProperty.KEY_WORD_MODIFIER);
        list_word.get(3).setProperty(WordProperty.CUSTOM_VARIABLE_NAME);
    }

    private void handleRow(Row row) {
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

    private SyntaxNode handleWord(Word word, LinkedList<SyntaxNode> node_list, Word next_word) {
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

    private SyntaxNode setWord(Word word, LinkedList<SyntaxNode> node_list, Word next_word) {
        for (SyntaxNode node : node_list) {
            //根据具体情况来分析
            switch (node.getTag()) {
//                case WordProperty.RETURN_TYPE:
//                    if (next_word != null && next_word.toString().contains("(")) {
//                        word.setProperty(node.getTag());
//                        return node;
//                    }
//                    break;
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
//                    Log.d("debug", "Other SyntaxNode" + node.getName() + "tag:" + node.getTag());
            }
        }
        return null;
    }
}
