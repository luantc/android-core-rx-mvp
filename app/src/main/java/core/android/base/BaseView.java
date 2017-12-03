package core.android.base;

public interface BaseView {
    void showLoadingDialog(String msg);
    void hideLoadingDialog();
    void onError(String error);
}
