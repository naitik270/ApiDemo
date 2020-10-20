package com.jakb.apidemo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jakb.apidemo.Categories.AdapterCategories;
import com.jakb.apidemo.Classes.ClsList;
import com.jakb.apidemo.Classes.StickerSelectActivity;
import com.jakb.apidemo.MyClass.AdapterThumbListNew;
import com.jakb.apidemo.MyClass.ApiCallBackInterface;
import com.jakb.apidemo.MyClass.ImageList;
import com.jakb.apidemo.MyClass.MainResponse;
import com.jakb.apidemo.entity.ImageEntity;
import com.jakb.apidemo.viewmodel.Layer;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jakb.apidemo.Global.ConnectionCheckBroadcast.CheckInternetConnection;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView recyclerView_categories;
    ProgressBar progress_bar;
    private ThumbViewModel thumbViewModel;
    private ProgressDialog pd;
    ImageView iv_main_img;
    ImageView iv_sticker;
    AdapterThumbListNew adapterThumbListNew;
    AdapterThumbList adapterThumbList;
    AdapterCategories adapterCategories;
    Button btn_sticker;
    TextView txt_no_data;
    TextView txt_path;

    public static final int SELECT_STICKER_REQUEST_CODE = 123;
    protected MotionView motionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        main();
    }

    public static ProgressDialog _prProgressDialog(Context c, String msg, Boolean setCancelable) {
        ProgressDialog progressDialog = new ProgressDialog(c, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(msg); // Setting Message
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.setCancelable(setCancelable);
        return progressDialog;
    }

    private void main() {
        thumbViewModel = new ViewModelProvider(this).get(ThumbViewModel.class);

        txt_path = findViewById(R.id.txt_path);
        txt_no_data = findViewById(R.id.txt_no_data);
        iv_sticker = findViewById(R.id.iv_sticker);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView_categories = findViewById(R.id.recyclerView_categories);
        motionView = findViewById(R.id.motionView);
        iv_main_img = findViewById(R.id.iv_main_img);
        progress_bar = findViewById(R.id.progress_bar);
        btn_sticker = findViewById(R.id.btn_sticker);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView_categories.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

        adapterThumbList = new AdapterThumbList(SecondActivity.this);
        recyclerView.setAdapter(adapterThumbList);

        adapterCategories = new AdapterCategories(SecondActivity.this);
        recyclerView_categories.setAdapter(adapterCategories);

//        getThumbNew();
//        getCategoryNameList();
//
//        adapterThumbList.setOnThumbImgClick((obj, position) -> {
//            Log.e("--params--", "obj: " + obj.getImage());
//            if (!obj.isDownloaded()) {
//                new DownloadImage(obj.getImage()).execute(obj.getImageUrl());
//                obj.setDownloaded(true);
//                adapterThumbList.notifyDataSetChanged();
//            } else {
//
//                String root = getExternalFilesDir(null).toString();
//                File path = new File(root + "/AAAAA/" + obj.getImage());
//                Log.e("--params--", "path: " + path);
//                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//                Log.e("--params--", "bmOptions: " + bmOptions);
//                Bitmap bitmap = BitmapFactory.decodeFile(path.getAbsolutePath(), bmOptions);
//                Log.e("--params--", "bitmap: " + bitmap);
////                bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
//                iv_sticker.setImageBitmap(bitmap);
//                addSticker(bitmap);
//
//            }
//        });


        adapterThumbList.setOnThumbImgClick((obj, position) -> {
            Log.e("--params--", "obj: " + obj.getImage());


            if (!obj.isDownloaded()) {

                if (CheckInternetConnection(SecondActivity.this)) {

                    new DownloadImage(obj.getImage()).execute(obj.getImageUrl());
                    obj.setDownloaded(true);
                    adapterThumbList.notifyDataSetChanged();

                } else {
                    Toast.makeText(SecondActivity.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                }

            } else {
//                pd = _prProgressDialog(SecondActivity.this,
//                        "Loading Image...", false);
//                pd.setIndeterminate(false);
//                pd.show();

                new Thread(() -> {
                    try {
                        URL url = null;
                        try {
                            url = new URL(obj.getThumbUrl());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        runOnUiThread(() -> {
//                            if (pd != null && pd.isShowing()) {
//                                pd.dismiss();
//                            }

                            if (bitmap != null) {
                                iv_sticker.setImageBitmap(bitmap);
                                addSticker(bitmap);
                            } else {
                                Toast.makeText(this, "Failed to load image!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e) {
                        Log.e("--params--", "Exception: " + e.getMessage());
                    }
                }).start();
            }
        });

        adapterCategories.setOnThumbImgClick((obj, position) -> {
            Log.e("--params--", "obj: " + obj.getThumbUrl());

//            File[] files = getBaseContext().getCacheDir().listFiles();
//            for (File file : files) {
//                Log.e("--params--", "files: " + file);
//            }
//            File cacheDirectory = this.getCacheDir();
//            Log.e("--params--", "cacheDirectory: " + cacheDirectory);
//            txt_path.setText(cacheDirectory.toString());


            deleteCache(SecondActivity.this);
        });

        btn_sticker.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), StickerSelectActivity.class);
            startActivityForResult(intent, SELECT_STICKER_REQUEST_CODE);
        });
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            Log.e("--params--", "dir: " + dir);
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        Log.e("--params--", "deleteDir: " + dir);
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                Log.e("--params--", "success: " + success);
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (CheckInternetConnection(SecondActivity.this)) {
            txt_no_data.setVisibility(View.GONE);
            getThumbNew();
            getCategoryNameList();
        } else {
            txt_no_data.setVisibility(View.VISIBLE);
            Toast.makeText(SecondActivity.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }


    }

    @SuppressLint("StaticFieldLeak")
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        String imgName;

        DownloadImage(String imgName) {
            this.imgName = imgName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = _prProgressDialog(SecondActivity.this,
                    "Download Image...", false);
            pd.setIndeterminate(false);
            pd.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
//
            URL url = null;
            try {
                url = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Bitmap bitmap = null;
            try {
                assert url != null;
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            String root = getExternalFilesDir(null).toString();
            File path = new File(root + "/AAAAA");

            if (!path.exists()) {
                path.mkdirs();
            }

//            File imageFile = new File(path, String.valueOf(System.currentTimeMillis()) + ".png");
            File imageFile = new File(path, imgName);
            Log.e("--params--", "imageFile: " + imageFile);
            FileOutputStream out = null;

            if (imageFile.exists()) {
                Log.e("--params--", "Already exists: ");
            } else {
                Log.e("--params--", "New file: ");

                try {
                    out = new FileOutputStream(imageFile);
                } catch (
                        FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // Compress Image
                    out.flush();
                    out.close();
                    // Tell the media scanner about the new file so that it is
                    // immediately available to the user.
                    MediaScannerConnection.scanFile(SecondActivity.this,
                            new String[]{imageFile.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                }
                            });
                } catch (Exception ignored) {
                }
            }
            return bitmap;
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView

//            iv_sticker.setVisibility(View.VISIBLE);
            iv_sticker.setImageBitmap(result);

            addSticker(result);
            pd.dismiss();
        }
    }

    void getThumbNew() {
        progress_bar.setVisibility(View.VISIBLE);

        thumbViewModel.getThumbImgList()
                .observe(this, clsData -> {
                    txt_no_data.setVisibility(View.VISIBLE);
                    String msg = clsData.getMessage();
                    if (msg != null && msg.equalsIgnoreCase("Success")) {

                        txt_no_data.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        if (clsData.getData().getList().size() > 0) {
                            List<ClsList> list = clsData.getData().getList();
                            iv_main_img.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            new Thread(() -> {

                                for (ClsList image : list) {
                                    if (new File(getExternalFilesDir(null).toString()
                                            + "/AAAAA", image.getImage()).exists()) {
                                        image.setDownloaded(true);
                                        list.set(list.indexOf(image), image);
                                    }
                                }

                                Gson gson = new Gson();
                                String jsonInString = gson.toJson(list);
                                Log.d("Check", "list " + jsonInString);
                                runOnUiThread(() -> {
                                    progress_bar.setVisibility(View.GONE);
                                    adapterThumbList.addList(list);
                                });

                            }).start();


                        } else {
                            progress_bar.setVisibility(View.GONE);
                            iv_main_img.setVisibility(View.GONE);
                            txt_no_data.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    } else {
                        progress_bar.setVisibility(View.GONE);
                        txt_no_data.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(SecondActivity.this, "No Data found...!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    void getCategoryNameList() {
        progress_bar.setVisibility(View.VISIBLE);
        thumbViewModel.getCategoriesName().observe(this, clsData -> {
            txt_no_data.setVisibility(View.VISIBLE);
            String msg = clsData.getMessage();

            if (msg != null && msg.equalsIgnoreCase("Success")) {
                progress_bar.setVisibility(View.GONE);
                txt_no_data.setVisibility(View.GONE);
                recyclerView_categories.setVisibility(View.GONE);

                if (clsData.getData().getList().size() > 0) {
                    iv_main_img.setVisibility(View.VISIBLE);
                    recyclerView_categories.setVisibility(View.VISIBLE);
                    adapterCategories.addList(clsData.getData().getList());
                } else {
                    progress_bar.setVisibility(View.GONE);
                    iv_main_img.setVisibility(View.GONE);
                    txt_no_data.setVisibility(View.VISIBLE);
                    recyclerView_categories.setVisibility(View.GONE);
                }
            } else {
                progress_bar.setVisibility(View.GONE);
                txt_no_data.setVisibility(View.VISIBLE);
                recyclerView_categories.setVisibility(View.GONE);
                Toast.makeText(SecondActivity.this, "No Data found...!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getThumbList() {

        String url = "http://34.231.230.242:3535/api/stickers/m/list";
        Log.e("--val--", "resurlponse: " + url);
        ApiCallBackInterface mainInterface = ApiClient.getRetrofitInstance()
                .create(ApiCallBackInterface.class);
        Log.e("--val--", "ApiCallBackInterface: " + mainInterface);

        Call<MainResponse> call = mainInterface.getImagesList(url);

        Log.e("--val--", "call: " + call);
        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {

                Gson gson2 = new Gson();
                String jsonInString2 = gson2.toJson(response.body());
                Log.e("--val--", "response: " + jsonInString2);

                if (response.body() != null && response.isSuccessful()) {
                    // when you get Successful response.
                    adapterThumbListNew.AddItem(response.body().getData().getLists());
                }

            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.e("--val--", "onFailure: " + t);
            }
        });
    }


    private void addSticker(Bitmap pica) {
        motionView.post(new Runnable() {
            @Override
            public void run() {
                Layer layer = new Layer();
                ImageEntity entity = new ImageEntity(layer, pica, motionView.getWidth(), motionView.getHeight());
                motionView.addEntityAndPosition(entity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_STICKER_REQUEST_CODE) {
                if (data != null) {
                    int stickerId = data.getIntExtra(StickerSelectActivity.EXTRA_STICKER_ID, 0);
                    Log.e("--params--", "stickerId: " + stickerId);
                    if (stickerId != 0) {
                        addSticker(BitmapFactory.decodeResource(getResources(), stickerId));
                    }
                }
            }
        }
    }


//    private void addSticker(final int stickerResId) {
//        motionView.post(new Runnable() {
//            @Override
//            public void run() {
//                Layer layer = new Layer();
//                Bitmap pica = BitmapFactory.decodeResource(getResources(), stickerResId);
//                Log.e("--params--", "pica: " + pica);
//                ImageEntity entity = new ImageEntity(layer, pica, motionView.getWidth(), motionView.getHeight());
//                Log.e("--params--", "entity: " + entity);
//                Log.e("--params--", "getWidth: " + motionView.getWidth());
//                Log.e("--params--", "getHeight: " + motionView.getHeight());
//
//                motionView.addEntityAndPosition(entity);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == SELECT_STICKER_REQUEST_CODE) {
//                if (data != null) {
//                    int stickerId = data.getIntExtra(StickerSelectActivity.EXTRA_STICKER_ID, 0);
//                    Log.e("--params--", "stickerId: " + stickerId);
//                    if (stickerId != 0) {
//                        addSticker(stickerId);
//                    }
//                }
//            }
//        }
//    }

}
