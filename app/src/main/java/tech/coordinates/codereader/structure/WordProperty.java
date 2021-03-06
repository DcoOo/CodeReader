package tech.coordinates.codereader.structure;

/**
 * Created by Administrator on 2016/11/13.
 */
public abstract class WordProperty {

    public static final int KEY_WORD_PACKAGE_CONTROL = 0;
    public static final int KEY_WORD_ROUTINE_CONTROL = 1;
    public static final int KEY_WORD_ERROR_HANDLE = 2;
    public static final int KEY_WORD_PACKAGE = 3;
    public static final int KEY_WORD_BASIC_TYPE = 4;
    public static final int KEY_WORD_VARIABLE_LINK = 5;//变量引用
    public static final int KEY_WORD_MODIFIER = 6;
    public static final int KEY_WORD_RETAINS = 7;

    public static final int CUSTOM_VARIABLE_NAME = 8;
    public static final int CUSTOM_FUNCTION_NAME = 9;
    public static final int TYPE_NAME = 10;
    public static final int RETURN_TYPE = 11;
    public static final int INTERFACE_NAME = 12;

    public static final int PACKAGE_NAME = 13;
    public static final int IMPORT_PACKAGE_NAME = 14;
    public static final int EXPLAIN = 15;
    public static final int DEFINE_NEW = 16;
    public static final int OPERATOR_ASSIGNMENT = 17;
    public static final int IF_CONDITION = 18;
    public static final int WHILE_CONDITION = 19;
    public static final int SWITCH_SELECT = 20;

    public static final int UNDEFINE = 21;

    public static final int FOR_CONDITION = 22;
}

