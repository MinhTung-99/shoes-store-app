package com.shoes_store_app.view.authentication;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentLoginBinding;
import com.shoes_store_app.fingerprint.FingerprintHandle;
import com.shoes_store_app.network.response.UserResponse;
import com.shoes_store_app.utils.Constant;
import com.shoes_store_app.utils.DelayUtil;
import com.shoes_store_app.utils.PreferenceManager;
import com.shoes_store_app.view.activity.AdminActivity;
import com.shoes_store_app.view.activity.AuthenticationActivity;
import com.shoes_store_app.view.activity.MainActivity;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;
    public Integer userId;
    private static LoginFragment loginFragment;
    public static LoginFragment getInstance() {
        return loginFragment;
    }

    private KeyStore keyStore;
    private final static String KEY_NAME = "EDMTDev";
    private Cipher cipher;
    private boolean isRemember;
    private PreferenceManager preferenceManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginFragment = this;
        preferenceManager = new PreferenceManager(requireContext());

        binding.txtSignUp.setOnClickListener(v -> ((AuthenticationActivity) requireActivity()).getNavigator().push(new RegisFragment()));

        binding.btnSignUp.setOnClickListener(v -> callApiGetUser());

        if (preferenceManager.getString(Constant.EMAIL) != null && preferenceManager.getString(Constant.PASSWORD) != null) {
            binding.edtEmail.setText(preferenceManager.getString(Constant.EMAIL));
            binding.edtPassword.setText(preferenceManager.getString(Constant.PASSWORD));
            binding.chkRememberPassword.setChecked(true);
            fingerPrinter();
            binding.btnSignUp.setVisibility(View.INVISIBLE);
        }

        binding.chkRememberPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            fingerPrinter();
        });
    }

    private void fingerPrinter () {
        KeyguardManager keyguardManager = (KeyguardManager) getActivity().getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = (FingerprintManager) getActivity().getSystemService(FINGERPRINT_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(getActivity(), "not enable", Toast.LENGTH_SHORT).show();
        } else {
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(getActivity(), "in setting", Toast.LENGTH_SHORT).show();
            } else {
                if (!keyguardManager.isKeyguardSecure()) {
                    Toast.makeText(getActivity(), "enabled setting", Toast.LENGTH_SHORT).show();
                } else {
                    genKey();

                    if (cipherInit()) {
                        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                        FingerprintHandle handler = new FingerprintHandle(getActivity(), () -> {
                            preferenceManager.putString(Constant.EMAIL, binding.edtEmail.getText().toString());
                            preferenceManager.putString(Constant.PASSWORD, binding.edtPassword.getText().toString());

                            callApiGetUser();
                        });
                        handler.startAuthentication(fingerprintManager, cryptoObject);
                        if (!binding.chkRememberPassword.isChecked()) {
                            handler.stopFingerAuth();
                            binding.btnSignUp.setVisibility(View.VISIBLE);
                        } else {
                            binding.btnSignUp.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onSuccessGetUser(List<UserResponse> userResponse) {
        boolean isLogin = false;
        for (UserResponse user : userResponse) {
            if (user.getEmail().equals(binding.edtEmail.getText().toString())
                    && user.getPassword().equals(binding.edtPassword.getText().toString())
                    && user.getStatus() == 1) {
                isLogin = true;

                userId = user.getId();
                Intent intent;
                if (user.getRoleId() == 3) {
                    intent = new Intent(getActivity(), AdminActivity.class);
                } else {
                    intent = new Intent(getActivity(), MainActivity.class);
                }
                startActivity(intent);
                requireActivity().finish();

                break;
            }
        }
        if (!isLogin) {
            Toast.makeText(getContext(), "Tài khoảng hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/" +KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7
            );
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | CertificateException | IOException | KeyStoreException | UnrecoverableKeyException | InvalidKeyException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void genKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }

        try {
            keyStore.load(null);
        } catch (CertificateException | IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        keyGenerator.generateKey();
    }
}
