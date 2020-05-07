package com.baiheng.glidestudy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image_view);
        Glide.with(this)
                .load("http://guolin.tech/book.png")
                .preload();
    }

    public void loadImage(View view) {
        String url = "http://guolin.tech/book.png";
//        url = null;
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .error(new ColorDrawable(Color.RED))
//                .fallback(new ColorDrawable(Color.CYAN))
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(this)
                .load(url)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        Toast.makeText(MainActivity.this,"图片加载完成",Toast.LENGTH_LONG).show();
//                        return false;
//                    }
//                })
                .into(imageView);

//        String url = "http://guolin.tech/book.png";
//        RequestOptions options = new RequestOptions()
//                .transforms(new BlurTransformation(), new GrayscaleTransformation());
//        Glide.with(this)
//                .load(url)
//                .apply(options)
//                .into(imageView);

    }
}
