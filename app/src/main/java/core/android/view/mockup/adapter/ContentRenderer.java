package core.android.view.mockup.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.scglab.common.listadapter.ItemRenderer;



public class ContentRenderer extends ItemRenderer<ContentItem> {

    ImageView iv_icon;
    TextView tv_title;
    TextView tv_content;
    TextView tv_skip;

    public ContentRenderer(View view) {
        super(view);

    }

    @Override
    protected void onBind(ContentItem item) {
        tv_title.setText(item.getTitle());
        iv_icon.setImageResource(item.getIcon());
        tv_content.setText(item.getContent());
        tv_skip.setText(item.getNext());
        tv_content.setVisibility(item.isShowContent()? View.VISIBLE: View.GONE);
        tv_skip.setVisibility(item.isShowNext()? View.VISIBLE: View.GONE);
    }
}
