package com.arrowwould.collegeapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arrowwould.collegeapp.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;


    ImageSlider image_slider;


    private List<String> imageUrls;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        image_slider = view.findViewById(R.id.image_slider);

        final List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/my-college-app-4fb7d.appspot.com/o/banner_image%2Fbanner_image_one?alt=media&token=881074b1-4080-457c-b8b8-2143aa5a0178", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/my-college-app-4fb7d.appspot.com/o/banner_image%2Fbanner_image_tow?alt=media&token=70631b70-a530-4b3e-99ff-171c56ecd02d", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/my-college-app-4fb7d.appspot.com/o/banner_image%2Fbanner_image_three?alt=media&token=27c5258d-28a7-4062-afed-ce5a8c6de63b", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/my-college-app-4fb7d.appspot.com/o/banner_image%2Fbanner_image_four?alt=media&token=61db3379-acd2-4a85-891e-4f58ec234238", ScaleTypes.FIT));

        image_slider.setImageList(slideModels);


        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Ratnasagar Buddha Vihar Minche Maharashtra");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }


}