package com.baiheng.okhttpstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Okhttp使用示例
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.request_btn);
        button.setOnClickListener(view -> networkRequest());
    }

    private void networkRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File(MainActivity.this.getExternalFilesDir(null), "avatar.png");
                    if (!file.exists()) {
                        Log.d("okHttpPostFormData", "文件不存在");
                        return;
                    }
                    RequestBody requestBodyFile =
                            RequestBody.create(MediaType.parse("application/octet-stream"), file);

                    MultipartBody multipartBody = new MultipartBody.Builder()
                            //一定要设置这句
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("username", "admin")
                            .addFormDataPart("password", "admin")
                            .addFormDataPart("avatar", "avatar.png", requestBodyFile)
                            .build();

                    OkHttpClient client = new OkHttpClient();	//创建OkHttpClient（全局最好只创建一个）
                    //创建Request对象（通过Request.Builder创建）
                    Request.Builder builder = new Request.Builder();
                    FormBody formBody = new FormBody.Builder()
                            .add("username", "admin")
                            .add("password", "admin")
                            .build();
                    Request request = builder.url("https://www.baidu.com")	//请求地址
                            .post(multipartBody)	//Post请求方法，不写默认为GET
                            .build();
                    Call call = client.newCall(request);	//创建一个Call对象
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            //请求失败
                            Log.d(TAG,"onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //对Reponse进行处理
                            String string = response.body().string();
                            Log.d(TAG,"response" + string);
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
