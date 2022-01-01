package com.shoes_store_app;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.shoes_store_app.network.ApiUtils;
import com.shoes_store_app.network.request.ProductAddItemRequest;
import com.shoes_store_app.network.request.ProductAddRequest;
import com.shoes_store_app.network.request.UserRequest;
import com.shoes_store_app.network.request.UserUpdateRequest;
import com.shoes_store_app.network.response.ProductItemResponse;
import com.shoes_store_app.network.response.ProductResponse;
import com.shoes_store_app.network.response.UserPostResponse;
import com.shoes_store_app.network.response.UserResponse;

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

    protected void callApiUpdateUser (String email, UserUpdateRequest userUpdateRequest) {
        ApiUtils.getApiService()
                .updateUser(email, userUpdateRequest)
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
                        onSuccessUpdateUser();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessUpdateUser () {}

    protected void callApiGetProduct () {
        ApiUtils.getApiService()
                .getProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ProductResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull List<ProductResponse> productResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessGetProduct(productResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessGetProduct (List<ProductResponse> productResponses) {}

    protected void callApiAddProduct (ProductAddRequest productAddRequest) {
        ApiUtils.getApiService()
                .addProduct(productAddRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserPostResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull UserPostResponse productResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessAddProduct();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessAddProduct () {}

    protected void callApiGetProductItem () {
        ApiUtils.getApiService()
                .getProductItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ProductItemResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull List<ProductItemResponse> productResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessGetProductItem(productResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessGetProductItem (List<ProductItemResponse> productResponses) {}

    protected void callApiGetProductItemById (int itemId) {
        ApiUtils.getApiService()
                .getProductItemById(itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProductItemResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull ProductItemResponse productResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessGetProductItemById(productResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessGetProductItemById (ProductItemResponse productResponses) {}

    protected void callApiAddProductItem (ProductAddItemRequest productAddItemRequest) {
        ApiUtils.getApiService()
                .addProductItem(productAddItemRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserPostResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull UserPostResponse productResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessAddProductItem();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
    protected void onSuccessAddProductItem () {}

    protected void callApiUpdateProductItem (ProductAddItemRequest productAddItemRequest) {
        ApiUtils.getApiService()
                .updateProductItem(productAddItemRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserPostResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CustomProgressDialogFragment.show(((BaseActivity) getActivity()));
                    }

                    @Override
                    public void onSuccess(@NonNull UserPostResponse productResponse) {
                        CustomProgressDialogFragment.hide();
                        onSuccessUpdateProductItem();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("KMFG", e.getLocalizedMessage());
                    }
                });
    }
    protected void onSuccessUpdateProductItem () {}

    protected void listenEdittextChange (EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextChange(s.toString(), editText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    protected void onTextChange (String text, EditText editText) {}
}
