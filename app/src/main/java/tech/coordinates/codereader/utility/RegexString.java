package tech.coordinates.codereader.utility;

/**
 * Created by Administrator on 2016/10/1.
 * 该类创建用于保存正则表达式的字符串
 */
public abstract class RegexString {


    public static final String COMMON_STR = "\".*\"";
    public static final String COMMON_COMMENT1 = "//.*"; //该类型注释
    public static final String COMMON_COMMENT2 = "/\\*+[\\s\\S]*?\\*+/";
//    public static final String COMMON_COMMENT3 = "^/\\*.*\\*/$";
//  /\*+([^/]*)\*+/
    public static final String JAVA_ACCESS_CONTROL = "\\bprivate\\b|\\bprotected\\b|\\bpublic\\b|\\bimport\\b|\\bpackage\\b";
    public static final String JAVA_MODIFIER = "\\babstract\\b|\\bclass\\b|\\bextends\\b|\\bfinal\\b" +
            "|\\binterface\\b|\\bimplements\\b|\\bnative\\b|\\bnew\\b" +
            "|\\bstatic\\b|\\bstrictfp\\b|\\bvolatile\\b|\\btransient\\b|\\bsynchronized\\b";
    public static final String JAVA_ROUTINE_CONTROL = "\\bbreak\\b|\\bcontinue\\b|\\breturn\\b|" +
            "\\bdo\\b|\\bwhile\\b|\\bif\\b|\\belse\\b|\\bfor\\b|\\binstanceof\\b|\\bswitch\\b|\\bcase\\b|\\bdefault\\b";
    public static final String JAVA_EXCEPTION = "\\btry\\b|\\bcatch\\b|\\bthrow\\b|\\bthrows\\b";
    public static final String JAVA_BASIC_TYPE = "\\bboolean\\b|\\bbyte\\b|\\bint\\b|\\bfloat\\b|\\bdouble\\b|\\bchar\\b|\\blong\\b|\\bshort\\b|\\bnull\\b|\\btrue\\b|\\bfalse\\b";
    public static final String JAVA_VARIABLE_LEAD = "\\bsuper\\b|\\bthis\\b|\\bvoid\\b";
    public static final String JAVA_REMAIN_WORDS = "\\bgoto\\b|\\bconst\\b";
    public static final String JAVA_EXPLAIN_NOTA = "\\@.*";

}
