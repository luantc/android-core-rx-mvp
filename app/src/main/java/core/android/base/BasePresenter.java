package core.android.base;

public interface BasePresenter<T>{

    void attachView(T view);

    void detachView();
}
