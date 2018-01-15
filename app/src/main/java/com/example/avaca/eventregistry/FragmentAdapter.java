package com.example.avaca.eventregistry;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.avaca.eventregistry.Fragments.Full_Article;
import com.example.avaca.eventregistry.Fragments.GoogleMaps;
import com.example.avaca.eventregistry.Fragments.New_ListView;
import com.example.avaca.eventregistry.NewsFromat.BaseNews;

import java.util.ArrayList;

/**
 * Created by avaca on 1/11/2018.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {



    private final FragmentManager mFragmentManager;


    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }


    @Override
    public Fragment getItem(int position) {
        Log.d("FragmentAdapter", "getItem: is called and its postion is" + position);
        if (position == 0) {
            return  New_ListView.create();
        } else if (position == 1){
            return GoogleMaps.create();
        } else if (position == 2){
            return Full_Article.create();
        }
        return null;
    }



    @Override
    public int getCount() {
        return 3;
    }
}
