package ru.pushapp.backgroungonmobile;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import ru.pushapp.backgroungonmobile.adapters.rvAdapter;

public class CategoryActivity extends AppCompatActivity{

    ArrayList<String> urlList = new ArrayList<>();
    ArrayList<ImageModel> imagesList = new ArrayList<>();

    Toolbar toolbar;
    ActionBar actionbar;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41314.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41296.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41136.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41100.jpg");

        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40963.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40552.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40429.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40418.jpg");

        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40963.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40552.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40429.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40418.jpg");

        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40963.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40552.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40429.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-40418.jpg");

        for (String itemURL: urlList) {
            ImageModel imageModel = new ImageModel();

            imageModel.setUrl(itemURL);
            imageModel.setCountDownload(123);
            imageModel.setCountLikes(321123);

            imagesList.add(imageModel);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        recyclerView = findViewById(R.id.fragment_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        rvAdapter adapter = new rvAdapter(this, imagesList, 2);
        recyclerView.setAdapter(adapter);

        actionbar.setTitle(getIntent().getStringExtra("ImageCategory"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.category_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                Toast.makeText(this,"Try search",Toast.LENGTH_LONG).show();
                break;
                default:finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
