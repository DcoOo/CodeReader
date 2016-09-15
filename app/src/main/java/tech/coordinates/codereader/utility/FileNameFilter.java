package tech.coordinates.codereader.utility;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/9/15.
 */
public class FileNameFilter implements FilenameFilter {
    /**
     * 目前先简单的可以显示这几种文件
     */
    private static String Str_Regex = ".*/.[java|py|c|cpp|xml|txt]";

    Pattern pattern = Pattern.compile(Str_Regex);
    @Override
    public boolean accept(File file, String s) {
        return pattern.matcher(s).matches();
    }
}
