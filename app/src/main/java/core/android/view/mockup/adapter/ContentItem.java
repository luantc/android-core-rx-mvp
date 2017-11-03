package core.android.view.mockup.adapter;

import com.scglab.common.listadapter.FlexAdapter;



@FlexAdapter.Item
public class ContentItem {
    private int icon;
    private String title;
    private String content;
    private String next;
    private boolean showContent;
    private boolean showNext;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public boolean isShowContent() {
        return showContent;
    }

    public void setShowContent(boolean showContent) {
        this.showContent = showContent;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public ContentItem(int icon, String title) {
        this.icon = icon;
        this.title = title;
        showContent=false;
        showNext=false;
    }

    public ContentItem(int icon, String title, String content, String next) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.next = next;
        showContent=true;
        showNext=true;
    }

    public ContentItem(int icon, String title, String next) {
        this.icon = icon;
        this.title = title;
        this.next = next;
        showNext=true;
        showContent=false;
    }
}
