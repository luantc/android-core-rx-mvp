package core.android.view.mockup.adapter;

import com.scglab.common.listadapter.FlexAdapter;



@FlexAdapter.Item
public class HeaderItem {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HeaderItem(String title) {
        this.title = title;
    }
}
