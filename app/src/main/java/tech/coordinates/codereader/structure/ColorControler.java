package tech.coordinates.codereader.structure;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16.
 */
public class ColorControler {

    public static final int COLOR_KEY_WORD_PACKAGE_CONTROL = 0;
    public static final int COLOR_KEY_WORD_ROUTINE_CONTROL = 1;
    public static final int COLOR_KEY_WORD_ERROR_HANDLE = 2;
    public static final int COLOR_KEY_WORD_PACKAGE = 3;
    public static final int COLOR_KEY_WORD_BASIC_TYPE = 4;
    //变量引用
    public static final int COLOR_KEY_WORD_VARIABLE_LINK = 5;
    public static final int COLOR_KEY_WORD_MODIFIER = 6;
    public static final int COLOR_KEY_WORD_RETAINS = 7;

    public static final int COLOR_CUSTOM_VARIABLE_NAME = 8;
    public static final int COLOR_CUSTOM_FUNCTION_NAME = 9;
    public static final int COLOR_TYPE_NAME = 10;
    public static final int COLOR_RETURN_TYPE = 11;
    public static final int COLOR_INTERFACE_NAME = 12;

    public static final int COLOR_PACKAGE_NAME = 13;
    public static final int COLOR_IMPORT_PACKAGE_NAME = 14;
    public static final int COLOR_EXPLAIN = 15;
    public static final int COLOR_DEFINE_NEW = 16;
    public static final int COLOR_OPERATOR_ASSIGNMENT = 17;
    public static final int COLOR_IF_CONDITION = 18;
    public static final int COLOR_WHILE_CONDITION = 19;
    public static final int COLOR_SWITCH_SELECT = 20;

    public static final int COLOR_UNDEFINE = 21;
    public static final int COLOR_FOR_CONDITION = 22;
    public static final int COLOR_UNKNOW = 999;

    public static HashMap<Integer,Integer> color_wordproperty = new HashMap<>();
    static {
        color_wordproperty.put(COLOR_KEY_WORD_PACKAGE_CONTROL,Color.BLUE);
        color_wordproperty.put(COLOR_KEY_WORD_ROUTINE_CONTROL,Color.BLACK);
        color_wordproperty.put(COLOR_KEY_WORD_ERROR_HANDLE,Color.BLACK);
        color_wordproperty.put(COLOR_KEY_WORD_PACKAGE,Color.BLACK);
        color_wordproperty.put(COLOR_KEY_WORD_BASIC_TYPE,Color.BLACK);
        color_wordproperty.put(COLOR_KEY_WORD_VARIABLE_LINK,Color.BLACK);
        color_wordproperty.put(COLOR_KEY_WORD_MODIFIER,Color.BLACK);
        color_wordproperty.put(COLOR_KEY_WORD_RETAINS,Color.BLACK);
        color_wordproperty.put(COLOR_CUSTOM_VARIABLE_NAME,Color.BLACK);
        color_wordproperty.put(COLOR_CUSTOM_FUNCTION_NAME,Color.BLACK);
        color_wordproperty.put(COLOR_TYPE_NAME,Color.BLACK);
        color_wordproperty.put(COLOR_RETURN_TYPE,Color.BLACK);
        color_wordproperty.put(COLOR_INTERFACE_NAME,Color.BLACK);
        color_wordproperty.put(COLOR_PACKAGE_NAME,Color.BLUE);
        color_wordproperty.put(COLOR_IMPORT_PACKAGE_NAME,Color.BLACK);
        color_wordproperty.put(COLOR_EXPLAIN,Color.BLACK);
        color_wordproperty.put(COLOR_DEFINE_NEW,Color.BLACK);
        color_wordproperty.put(COLOR_OPERATOR_ASSIGNMENT,Color.BLACK);
        color_wordproperty.put(COLOR_IF_CONDITION,Color.BLACK);
        color_wordproperty.put(COLOR_WHILE_CONDITION,Color.BLACK);
        color_wordproperty.put(COLOR_SWITCH_SELECT,Color.BLACK);
        color_wordproperty.put(COLOR_UNDEFINE,Color.BLACK);
        color_wordproperty.put(COLOR_FOR_CONDITION,Color.BLACK);

        color_wordproperty.put(COLOR_UNKNOW, Color.BLACK);
        color_wordproperty.put(RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT,Color.GREEN);
        color_wordproperty.put(RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT,Color.GREEN);
    }

}
