package tech.coordinates.codereader.structure;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/11/13.
 */
public class Word {
    //记录词性
    private int property = 999;
    private int start = -1;
    private int end = -1;
    private LinkedList<Character> word_content;

    public Word() {

    }

    public Word(String word) {
        word_content = new LinkedList<>();
        for (byte c : word.getBytes()) {
            word_content.add((char) c);
        }
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public void setWord_content(LinkedList<Character> word_content) {
        this.word_content = word_content;
    }

    public int getProperty() {

        return property;
    }

    public LinkedList<Character> getWord_content() {
        return word_content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Character c : word_content) {
            builder.append(c);
        }
        return builder.toString();
    }
}
