package com.example.m10_intents;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;

public class DrawOnPhotoActivity extends AppCompatActivity {

    private static final String TAG = "DrawOnPhotoActivity";
    private DrawingView drawingView;
    private Button btnChoosePhoto, btnClear;


    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        drawingView.setBackgroundImage(bitmap);
                        Log.d(TAG, "Image loaded successfully");
                    } catch (Exception e) {
                        Log.e(TAG, "Error loading image: " + e.getMessage());
                        Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "No image selected");
                }
            });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_on_photo);


        drawingView = findViewById(R.id.drawingView);
        btnChoosePhoto = findViewById(R.id.btnChoosePhoto);
        btnClear = findViewById(R.id.btnClear);


        btnChoosePhoto.setOnClickListener(v -> openGallery());


        btnClear.setOnClickListener(v -> drawingView.clearDrawing());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }
}
