package core.android.view.mockup.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.scglab.common.listadapter.FlexAdapter;
import com.scglab.common.listadapter.OnItemClickEventHandler;
import com.scglab.common.listadapter.RendererFactory;

import butterknife.BindView;
import core.android.R;
import core.android.base.BaseFragment;
import core.android.presenter.MockupBusPresenter;
import core.android.presenter.contract.MockupBusContract;
import core.android.view.mockup.adapter.ContentItem;
import core.android.view.mockup.adapter.ContentRenderer;
import core.android.view.mockup.adapter.HeaderItem;
import core.android.view.mockup.adapter.HeaderRenderer;

public class MockupFragment extends BaseFragment<MockupBusPresenter> implements MockupBusContract.View, OnItemClickEventHandler {

    @BindView(R.id.rv_account)
    RecyclerView rv_account;

    public static Fragment newInstance() {
        return new MockupFragment();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mockup_fragment;
    }

    @Override
    protected void initEventAndData() {
        RendererFactory rendererFactory = new RendererFactory();
        rendererFactory.put(HeaderRenderer.class, R.layout.header_item);
        rendererFactory.put(ContentRenderer.class, R.layout.content_item);
        FlexAdapter flexAdapter = new FlexAdapter(rendererFactory);
        rv_account.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        rv_account.setAdapter(flexAdapter);
        flexAdapter.addItem(new HeaderItem("TEST"));
        flexAdapter.addItem(new ContentItem(
                R.drawable.back,
                "AAAAAAAAAA",
                "AAAAAAAA",
                ""
        ));
        flexAdapter.addItem(new HeaderItem("TEST"));
        flexAdapter.addItem(new ContentItem(
                R.drawable.back,
                "AAAAAAAAAA",
                "AAAAAAAA",
                ""
        ));
        flexAdapter.addItem(new HeaderItem("TEST"));
        flexAdapter.addItem(new ContentItem(
                R.drawable.back,
                "AAAAAAAAAA",
                "AAAAAAAA",
                ""
        ));

        flexAdapter.setOnItemClickEventHandler(this);
    }

    @Override
    public void error(String error) {
        ToastUtils.showLong(error);
    }

    @Override
    public void onItemClick(Object item) {

    }

    @Override
    public void onItemLongClick(Object item) {

    }

    @Override
    public void onChildViewClick(Object item, int viewId) {

    }

    @Override
    public void onChildViewLongClick(Object item, int viewId) {

    }
}
