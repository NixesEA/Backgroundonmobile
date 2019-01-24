package ru.pushapp.backgroundonmobile.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.pushapp.backgroundonmobile.ImageModel;
import ru.pushapp.backgroundonmobile.R;
import ru.pushapp.backgroundonmobile.adapters.rvAdapter;

public class BestImageFragment extends Fragment {

    ArrayList<String> urlList = new ArrayList<>();
    ArrayList<ImageModel> imagesList = new ArrayList<>();


    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41314.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41296.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41136.jpg");
        urlList.add("https://mobimg.b-cdn.net/pic/v2/gallery/preview/abstrakciya-fon-41100.jpg");

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment, container, false);

        recyclerView = view.findViewById(R.id.fragment_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        rvAdapter adapter = new rvAdapter(getContext(), imagesList,3);
        recyclerView.setAdapter(adapter);
    }
}
