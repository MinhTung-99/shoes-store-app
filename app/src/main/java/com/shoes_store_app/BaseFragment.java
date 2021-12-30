package com.shoes_store_app;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.shoes_store_app.network.ApiUtils;
import com.shoes_store_app.network.response.UserResponse;
import com.shoes_store_app.view.activity.MainActivity;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BaseFragment extends Fragment {

    protected void callApiGetUser () {
        ApiUtils.getApiService()
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<UserResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull List<UserResponse> userResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessGetUser(userResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessGetUser (List<UserResponse> userResponse) {}

    protected void callApiGetUserById (Integer id) {
        ApiUtils.getApiService()
                .getUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull UserResponse userResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessGetUserById(userResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessGetUserById (UserResponse userResponse) {}
}
