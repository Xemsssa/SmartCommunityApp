package com.xemss.smartcommunityapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TopFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: 11.10.2017 create inflate fragment  
        return inflater.inflate(R.layout.fragment_top, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
