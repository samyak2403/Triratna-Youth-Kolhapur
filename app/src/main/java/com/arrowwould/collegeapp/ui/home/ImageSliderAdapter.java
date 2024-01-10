package com.arrowwould.collegeapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.arrowwould.collegeapp.R;
import com.bumptech.glide.Glide;
import java.util.List;

public class ImageSliderAdapter extends PagerAdapter {

    private Context context;
    private List<String> imageUrls;



    public ImageSliderAdapter(Context context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.fragment_home, container, false);




        ImageView imageView = slideLayout.findViewById(R.id.image_slider);
        Glide.with(context).load(imageUrls.get(position)).into(imageView);

        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
