package com.example.comp1424;

import android.os.Bundle;
import java.io.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

    public class SecondFragment extends Fragment {

        public SecondFragment(){
            // require a empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_second, container, false);
        }
    }