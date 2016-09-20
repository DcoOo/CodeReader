package tech.coordinates.codereader.adapter;

import android.content.Context;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

import tech.coordinates.codereader.R;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ShowFilesSimpleAdapter extends SimpleAdapter {
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */

    public ShowFilesSimpleAdapter(Context context, List<? extends Map<String, ?>> data) {
        super(context, data, R.layout.listview_item_show_file,new String[]{"name","path","isFile"},new int[]{R.id.tv_item_name,R.id.tv_item_path,R.id.tv_item_isFile});
    }
}
