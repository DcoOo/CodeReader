package tech.coordinates.codereader.structure;

import android.util.Log;
import android.view.View;

import java.util.LinkedList;

import tech.coordinates.codereader.utility.ContentUtility;
import tech.coordinates.codereader.utility.RegexString;

/**
 * Created by Administrator on 2016/11/14.
 */
public class HighLightUtil {

    private LinkedList<SyntaxNode> syntax_list = new TreeCreater().createSyntaxTree();
    public HighLightUtil(FileStructure structure, View view){
        for (Row row : structure.getRow_array_list()){
            if (row.getRowProperty() != RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT && row.getRowProperty() != RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT && !row.toString().equals("")){
                handleRow(row);
            }

        }

    }
    private void handleRow(Row row){
        LinkedList<Word> words = row.getList_words();
        if (row.getList_words().size() >= 1) {
//            Word firstWord = words.getFirst();
//            SyntaxNode next_syntax_node = null;
//            if (words.get(1) != null) {
//                Word secondWord = words.get(1);
                SyntaxNode next_syntax_node = null;
//                for (SyntaxNode node : syntax_list) {
//                    if (node.getName() != null && node.getName().equals(row.getList_words().get(0).toString())) {
//                        next_syntax_node = node;
//                        break;
//                    }
//                }
//                if (next_syntax_node == null)
//                    next_syntax_node = setWord(firstWord, syntax_list, secondWord);
//                LinkedList<SyntaxNode> node = new LinkedList<>();
//                node.add(next_syntax_node);
////            SyntaxNode next_syntac_node = handleWord(words.get(0),next_node)
                for (int i = 0; i < words.size(); i++) {
                    Word nextWord;
                    if (i == words.size()-1){
                        nextWord = null;
                    }else {
                        nextWord = words.get(i+1);
                    }
                    if (i == 0)
                        next_syntax_node = handleWord(words.get(i), syntax_list, nextWord);
                    else
                        next_syntax_node = handleWord(words.get(i), next_syntax_node.getChildNode(),nextWord);


                }

        }
    }

    private SyntaxNode handleWord(Word word,LinkedList<SyntaxNode> node_list,Word next_word){
        //当语法节点为空，说明到头
        //处理注解
        if (word.toString().matches(RegexString.JAVA_EXPLAIN_NOTA)){
            word.setProperty(WordProperty.EXPLAIN);
            return null;
        }
        if (node_list == null){
            return null;
        }
        if (node_list.size() == 1){
            word.setProperty(node_list.get(0).getTag());
            return node_list.get(0);
        }
        for (SyntaxNode node : node_list){
            if (word.toString().equals(node.getName())){
                word.setProperty(node.getTag());
                return node;
            }
        }
        return setWord(word,node_list,next_word);
    }

    private SyntaxNode setWord(Word word,LinkedList<SyntaxNode> node_list,Word next_word){
        for (SyntaxNode node : node_list){
            //根据具体情况来分析
            switch (node.getTag()){
                case WordProperty.RETURN_TYPE:
                    if (next_word != null && next_word.toString().contains("(")){
                        word.setProperty(node.getTag());
                        return node;
                    }
                    break;
                case WordProperty.CUSTOM_VARIABLE_NAME:
                    if (!word.toString().contains("(") && !word.toString().equals("extends") && !word.toString().equals("implements")){
                        word.setProperty(node.getTag());
                        return node;
                    }
                    break;
                case WordProperty.CUSTOM_CLASS_NAME:
                    if (next_word != null && !next_word.toString().contains("(")){
                        word.setProperty(node.getTag());
                        return node;
                    }
                    break;
                case WordProperty.CUSTOM_FUNCTION_NAME:
                    if (word.toString().contains("(")){
                        word.setProperty(node.getTag());
                        return node;
                    }
                    break;
                case WordProperty.INTERFACE_NAME:
                    word.setProperty(node.getTag());
                    return node;
                default:
                    Log.d("debug","Other SyntaxNode"+node.getName()+"tag:"+node.getTag());
            }
        }
        return null;
    }
}
