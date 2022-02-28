package com.example.stickerdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Bitmap bmImage;
    private Context context;
    private ImageView imgAngPow;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        imgAngPow = findViewById(R.id.imageView);
        button = findViewById(R.id.button);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(context).asBitmap().load("https://121.120.89.99/hlb-my-api/api/multipart/resources/ANGPOW_FESTIVE_IMG").diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                bmImage = resource;
                imgAngPow.setImageBitmap(bmImage);

            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImage();
            }


        });
    }



public  void sendImage(){
    try {

        Uri uri = getLocalBitmapUri(bmImage);

        Intent share = new Intent();
//            share.setType("text/plain");
        share.setPackage("com.whatsapp");

//        Uri uriPdf =
//                FileProvider.getUriForFile(
//                        context, context.getApplicationContext().getPackageName() + ".provider", response);

        //share.putExtra(Intent.EXTRA_TEXT, "Happy Chinese new year");
        // Set type to only show apps that can open your PNG file

        share.setType("image/jpeg");
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        imageUris.add(uri); // Add your image URIs here
       // imageUris.add(uriPdf);
        share.setAction(Intent.ACTION_SEND_MULTIPLE);
        share.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        share.putExtra(Intent.EXTRA_TEXT, "Message From Ray");
        //share.setType("application/*");

        // Set type to only show apps that can open your PNG file
        startActivity(Intent.createChooser(share, "send"));

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".jpeg");
        FileOutputStream out = null;
        try {
//            Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), Bitmap.Config.ARGB_8888);
           // int myColor = ContextCompat.getColor(context, R.color.colorAngYellow);
            Bitmap bitmap = getBitmapByBg(bmp,getResources().getColor(R.color.colorAngYellow));
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bmpUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    private Bitmap getBitmapByBg(Bitmap bitmap, int color){
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth(), bitmap.getHeight(), true);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(color);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }
}