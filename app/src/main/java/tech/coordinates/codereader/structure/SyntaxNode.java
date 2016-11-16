package tech.coordinates.codereader.structure;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/11/13.
 */
public class SyntaxNode {

    private boolean couldStart = false;
    private boolean couldEnd = false;
    private LinkedList<SyntaxNode> childNode = new LinkedList<>();
    private LinkedList<SyntaxNode> parent = new LinkedList<>();
    private int tag;
    private String name;

    public SyntaxNode() {

    }

    public void setCouldStart(boolean couldStart) {
        this.couldStart = couldStart;
    }

    public void setCouldEnd(boolean couldEnd) {
        this.couldEnd = couldEnd;
    }

    public void setChildNode(LinkedList<SyntaxNode> childNode) {
        this.childNode = childNode;
    }

    public void setParent(LinkedList<SyntaxNode> parent) {
        this.parent = parent;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCouldStart() {

        return couldStart;
    }

    public boolean isCouldEnd() {
        return couldEnd;
    }

    public LinkedList<SyntaxNode> getChildNode() {
        return childNode;
    }

    public LinkedList<SyntaxNode> getParent() {
        return parent;
    }

    public int getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }
}

//abstract class Tag{
//    public static final int TAG_MODIFIER = 0;
//    public static final int TAG_PACKAGE_CONTROL = 1;
//    public static final int TAG_ROUTINE_CONTROL = 2;
//    public static final int TAG_ERROR_HANDLE = 3;
//    public static final int TAG_PACKAGE = 4;
//    public static final int TAG_VARIABLE_LINK = 5;
//    public static final int TAG_RETAINS = 6;
//}
