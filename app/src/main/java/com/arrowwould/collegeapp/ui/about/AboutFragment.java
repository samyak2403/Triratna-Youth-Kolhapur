package com.arrowwould.collegeapp.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arrowwould.collegeapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {


    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_comp, "Computer Science", "Computer Science and Engineering started in year 2023. at present intake sheet in 1-year is 30 student. and in lateral entry is 6 "));
        list.add(new BranchModel(R.drawable.ic_mech, "Mechanical Production", "Mechanical Engineering (Production) started in year 2013. At present intake sheet in 1-year is 30 student. and in lateral entry is 6 "));

        adapter = new BranchAdapter(getContext(),list);

        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.college_image);

        Glide.with(getContext())
                .load("https://upload.wikimedia.org/wikipedia/commons/b/bd/Emmanuel_College_Front_Court%2C_Cambridge%2C_UK_-_Diliff.jpg")
                .into(imageView);


        return view;
    }
}