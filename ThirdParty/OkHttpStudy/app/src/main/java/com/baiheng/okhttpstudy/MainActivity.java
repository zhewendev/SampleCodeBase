package com.baiheng.okhttpstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
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
        OkHttpClient.Builder builder= new OkHttpClient.Builder();
        Request request = new Request();
    }

//    private void networkRequest() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    File file = new File(MainActivity.this.getExternalFilesDir(null), "avatar.png");
//                    if (!file.exists()) {
//                        Log.d("okHttpPostFormData", "文件不存在");
//                        return;
//                    }
////                    RequestBody requestBodyFile =
////                            RequestBody.create(MediaType.parse("application/octet-stream"), file);
//                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//                    ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, new ProgressRequestBody.okHttpUpLoadProgressListener() {
//                        @Override
//                        public void onRequestProgress(long byteWritten, long contentLength) {
//
//                        }
//                    });
//
//
//
//
//                    MultipartBody multipartBody = new MultipartBody.Builder()
//                            //一定要设置这句
//                            .setType(MultipartBody.FORM)
//                            .addFormDataPart("username", "admin")
//                            .addFormDataPart("password", "admin")
//                            .addFormDataPart("avatar", "avatar.png", requestBodyFile)
//                            .build();
//
//                    OkHttpClient client = new OkHttpClient();	//创建OkHttpClient（全局最好只创建一个）
//                    //创建Request对象（通过Request.Builder创建）
//                    Request.Builder builder = new Request.Builder();
//                    FormBody formBody = new FormBody.Builder()
//                            .add("username", "admin")
//                            .add("password", "admin")
//                            .build();
//                    Request request = builder.url("https://www.baidu.com")	//请求地址
//                            .post(multipartBody)	//Post请求方法，不写默认为GET
//                            .build();
//                    Call call = client.newCall(request);	//创建一个Call对象
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            //请求失败
//                            Log.d(TAG,"onFailure");
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            //对Reponse进行处理
//                            String string = response.body().string();
//                            Log.d(TAG,"response" + string);
//                        }
//
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    public void download(final String url, final String saveDir,
                         HashMap<String,String> params, HashMap<String,String> extraHeaders,
                         final OnDownloadListener listener) {

        OkHttpClient okHttpClient = new OkHttpClient();
        //构造请求Url
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            for (String key : params.keySet()) {
                if (params.get(key)!=null){
                    urlBuilder.setQueryParameter(key, params.get(key));//非必须
                }
            }
        }
        //构造请求request
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .headers(extraHeaders == null ? new Headers.Builder().build() : Headers.of(extraHeaders))//headers非必须
                .get()
                .build();

        //异步执行请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //非主线程
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);
                try {
                    //获取响应的字节流
                    is = response.body().byteStream();
                    //文件的总大小
                    long total = response.body().contentLength();
                    File file = new File(savePath);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    //循环读取输入流
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        if(listener != null){
                            listener.onDownloading(progress);
                        }

                    }
                    fos.flush();
                    // 下载完成
                    if(listener != null){
                        listener.onDownloadSuccess();
                    }

                } catch (Exception e) {
                    if(listener != null){
                        listener.onDownloadFailed();
                    }

                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    public interface OnDownloadListener {
        void onDownloadFailed();
        void onDownloading(int progress);
        void onDownloadSuccess();
    }

    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;

    }
}
