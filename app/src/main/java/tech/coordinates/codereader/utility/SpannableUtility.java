package tech.coordinates.codereader.utility;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/9/28.
 */
public class SpannableUtility {

    private static final String START = "START";
    private static final String END = "END";
    private static final int HIGH_LIGNT_BLUE = Color.BLUE;
    private static final String HIGH_LIGNT_GREEN = "#008B00";

    private static String s_content;

    public static SpannableString codeHighLight4JAVA(String content){
        if (content != null && !content.equals("")){
            List<Map<String,Integer>> list_map_words = new LinkedList<>();
            SpannableString span_str = null;
            s_content = content;
            span_str = new SpannableString(s_content);
//            span_color = new ForegroundColorSpan(DEFAULT_HIGH_LIGHT_COLOR);
            list_map_words.addAll(getStartAndEnd(RegexString.JAVA_ACCESS_CONTROL));
            list_map_words.addAll(getStartAndEnd(RegexString.JAVA_BASIC_TYPE));
            list_map_words.addAll(getStartAndEnd(RegexString.JAVA_EXCEPTION));
            list_map_words.addAll(getStartAndEnd(RegexString.JAVA_MODIFIER));
            list_map_words.addAll(getStartAndEnd(RegexString.JAVA_REMAIN_WORDS));
            list_map_words.addAll(getStartAndEnd(RegexString.JAVA_ROUTINE_CONTROL));
            list_map_words.addAll(getStartAndEnd(RegexString.JAVA_VARIABLE_LEAD));
            for (Map<String,Integer> m : list_map_words){
                Log.d("Debug","//////");
                //每次都需要新建一个Span，不然会取消之前的颜色。
                ForegroundColorSpan span_color = new ForegroundColorSpan(HIGH_LIGNT_BLUE);
                span_str.setSpan(span_color,m.get(START),m.get(END), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            list_map_words.clear();
            list_map_words.addAll(getStartAndEnd(RegexString.COMMON_STR));
            list_map_words.addAll(getStartAndEnd(RegexString.COMMON_COMMENT1));
            list_map_words.addAll(getStartAndEnd(RegexString.COMMON_COMMENT2));
            for (Map<String,Integer> m : list_map_words){
                ForegroundColorSpan span_color = new ForegroundColorSpan(Color.parseColor("#008B00"));
                span_str.setSpan(span_color,m.get(START),m.get(END), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            return span_str;
        }
        return null;
    }

    public static SpannableString codeHighLight4PY(){
        SpannableString span_str = null;
        return span_str;
    }

    public static SpannableString codeHighLight4CPP(){
        SpannableString span_str = null;
        return span_str;
    }

    public static SpannableString codeHighLight4CSHARP(){
        SpannableString span_str = null;
        return span_str;
    }

    private static List<Map<String,Integer>> getStartAndEnd(String str_regex){
        List<Map<String,Integer>> start_end = new LinkedList<>();
        Map<String,Integer> single;
        Matcher matcher = Pattern.compile(str_regex).matcher(s_content);
        Log.d("Debug",str_regex);
        while (matcher.find()){
            single = new HashMap<>();
            single.put(START,matcher.start());
            Log.d("Debug",matcher.start()+"********");
            single.put(END,matcher.end());
            start_end.add(single);
        }
        return start_end;
    }
}
