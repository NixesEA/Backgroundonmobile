package ru.pushapp.backgroungonmobile;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import ru.pushapp.backgroungonmobile.adapters.rvAdapter;

public class SelectedImageActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> urlList = new ArrayList<>();
    ArrayList<ImageModel> imagesList = new ArrayList<>();

    Toolbar toolbar;
    ActionBar actionbar;

    ImageView bigImageView;
    TextView countLikesTV;
    TextView countDownloadTV;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    String ImageURL;
    String imageCountLikes;
    String imageCountDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_image);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        actionbar.setTitle("");

        bigImageView = findViewById(R.id.big_imageview);
        countLikesTV = findViewById(R.id.count_likes);
        countDownloadTV = findViewById(R.id.count_downloads);
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(this);

        recyclerView = findViewById(R.id.similar_images_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41314.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41296.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41136.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41100.jpg");

        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40963.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40552.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40429.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40418.jpg");


        for (String itemURL : urlList) {
            ImageModel imageModel = new ImageModel();

            imageModel.setUrl(itemURL);
            imageModel.setCountDownload(123);
            imageModel.setCountLikes(321123);

            imagesList.add(imageModel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        ImageURL = getIntent().getStringExtra("ImageURL");
        imageCountLikes = getIntent().getStringExtra("ImageCountLikes");
        imageCountDownload = getIntent().getStringExtra("ImageCountDownload");

        countLikesTV.setText(imageCountLikes);
        countDownloadTV.setText(imageCountDownload);

        Glide.with(this)
                .load(ImageURL)
                .into(bigImageView);

        rvAdapter adapter = new rvAdapter(this, imagesList, 4);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.selected_image_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_image_in_favor:
                Toast.makeText(this, "Image added in favorites", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_share_image:
                sharingImage();
//                Toast.makeText(this, "Try sharing", Toast.LENGTH_LONG).show();
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void sharingImage() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("message/rfc822");
        shareIntent.putExtra(Intent.EXTRA_TEXT, ImageURL);
        startActivity(Intent.createChooser(shareIntent, "Выберите куда отправить картинку"));
    }

    @Override
    public void onClick(View view) {
        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        bigImageView.buildDrawingCache();
        try {
            Toast.makeText(this, "Обои устанавливаются", Toast.LENGTH_LONG).show();
            myWallpaperManager.setBitmap(bigImageView.getDrawingCache());
            Toast.makeText(this, "Обои успешно установлены", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
}
