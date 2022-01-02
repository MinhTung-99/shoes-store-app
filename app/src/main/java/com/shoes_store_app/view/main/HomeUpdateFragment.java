package com.shoes_store_app.view.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentHomeUpdateBinding;
import com.shoes_store_app.network.request.ProductAddItemRequest;
import com.shoes_store_app.network.response.ProductItemResponse;
import com.shoes_store_app.utils.ImageConvert;
import com.shoes_store_app.utils.ImageResizer;
import com.shoes_store_app.view.activity.AdminActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HomeUpdateFragment extends BaseFragment {

    private static final int REQUEST_PERMISSION = 1009;
    private int position;
    private FragmentHomeUpdateBinding binding;
    private HomeFragment.HomeCallback homeCallback;
    private String pathTakeImage;

    public HomeUpdateFragment(int position, HomeFragment.HomeCallback homeCallback) {
        this.position = position;
        this.homeCallback = homeCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeUpdateBinding.inflate(inflater, container, false);

        binding.btnUpdate.setOnClickListener(v -> {
            ProductAddItemRequest productAddItemRequest = new ProductAddItemRequest(
                    HomeFragment.getInstance().shoesType.get(position).getItemId(),
                    binding.txtProductName.getText().toString(),
                    binding.edtColor.getText().toString(),
                    binding.edtSize.getText().toString(),
                    binding.edtNumItems.getText().toString(),
                    binding.edtSale.getText().toString()
            );
            callApiUpdateProductItem(productAddItemRequest);
        });

        binding.imgShoes.setOnClickListener(v -> {
            showCamera();
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callApiGetProductItemById(HomeFragment.getInstance().shoesType.get(position).getItemId());
    }

    @Override
    protected void onSuccessGetProductItemById(ProductItemResponse productResponses) {
        if (productResponses.getImg() != null) {
            ImageConvert.getInstance().convertBase64ToBitmap(productResponses.getImg(), binding.imgShoes);
        }
        binding.txtProductName.setText(HomeFragment.getInstance().shoesType.get(position).getProductName());
        binding.edtColor.setText(productResponses.getColor());
        binding.edtNumItems.setText(String.valueOf(productResponses.getNumItems()));
        binding.edtSize.setText(String.valueOf(productResponses.getSize()));
        binding.edtSale.setText(String.valueOf(productResponses.getSale()));
    }

    @Override
    protected void onSuccessUpdateProductItem() {
        if (pathTakeImage != null) {
            Bitmap fullSizeBitmap = BitmapFactory.decodeFile(pathTakeImage);
            Bitmap reduceBitmap = ImageResizer.reduceBitmapSize(fullSizeBitmap, 900000);
            File file = saveBitmapToFile(reduceBitmap);

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getPath(), requestFile);
            callApiAddImageProductItem(HomeFragment.getInstance().shoesType.get(position).getItemId(), body);
        } else {
            homeCallback.callback();
            ((AdminActivity) requireActivity()).getNavigator().pop();
        }
    }

    @Override
    protected void onSuccessAddImageProductItem() {
        homeCallback.callback();
        ((AdminActivity) requireActivity()).getNavigator().pop();
    }

    @Override
    protected void onErrImageProductItem() {
        homeCallback.callback();
        ((AdminActivity) requireActivity()).getNavigator().pop();
    }

    public File saveBitmapToFile(Bitmap image) {
        OutputStream outputStream = null;
        File myDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File dir = new File(myDir.getAbsolutePath() + "/picture");
        dir.mkdir();
        File file = new File(dir, System.currentTimeMillis() + ".png");
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        image.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void showCamera() {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
        } else {
            Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (imageIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                File imageFile = null;
                try {
                    imageFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (imageFile != null) {
                    Uri photoUri = FileProvider.getUriForFile(requireActivity(),
                            requireActivity().getApplicationContext().getPackageName() + ".fileprovider",
                            imageFile);
                    imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    activityResult.launch(imageIntent);
                    //startActivityForResult(imageIntent, TAKE_PICTURE);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File file = new File(storageDir, timeStamp + ".jpg");
        file.createNewFile();
        pathTakeImage = file.getAbsolutePath();
        return file;
    }

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (pathTakeImage != null) {
                    binding.imgShoes.setImageURI(Uri.parse(pathTakeImage));
                }
            });
}
