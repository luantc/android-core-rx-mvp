package core.android.retrofit;

import core.android.model.entity.mockup.Account;
import core.android.model.entity.mockup.Token;

import io.reactivex.Flowable;

public class RetrofitClient {

    private APIs apis;

    public RetrofitClient(APIs apis) {
        this.apis = apis;
    }

    public Flowable<Token> mockup(Account account) {
        return apis.mockup(account);
    }

}
