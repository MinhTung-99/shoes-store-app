package com.shoes_store_app;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.shoes_store_app.network.ApiUtils;
import com.shoes_store_app.network.request.UserRequest;
import com.shoes_store_app.network.response.UserPostResponse;
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
                        Log.d("KMFG", e.getLocalizedMessage());
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

    protected void callApiAddUser (UserRequest userRequest) {
        ApiUtils.getApiService()
                .addUser(userRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserPostResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull UserPostResponse userResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessAddUser();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessAddUser () {}

    protected void callApiDeleteUser (int id) {
        ApiUtils.getApiService()
                .deleteUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserPostResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull UserPostResponse userResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessDeleteUser();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessDeleteUser () {}
}
