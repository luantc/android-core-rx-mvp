package core.android.view.mockup.adapter;

import android.view.View;
import android.widget.TextView;

import com.scglab.common.listadapter.ItemRenderer;


/**
 * Created by luantruong on 11/3/17.
 */

public class HeaderRenderer extends ItemRenderer<HeaderItem> {

    TextView tv_title;

    public HeaderRenderer(View view) {
        super(view);
    }

    @Override
    protected void onBind(HeaderItem item) {
        tv_title.setText(item.getTitle());
    }
}