package com.mkmcmxci.breakingbad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.breakingbad.R;
import com.mkmcmxci.breakingbad.viewmodel.ListViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    private ListViewModel mListViewModel;
    private ListAdapter mListAdapter;

    @BindView(R.id.fragment_list_recyclerview) RecyclerView mRecView;
    @BindView(R.id.fragment_list_progressBar) ProgressBar pBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recInit();
        vModelInit();

    }

    private void recInit() {

        mListAdapter = new ListAdapter(new ArrayList<>());
        mRecView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecView.setAdapter(mListAdapter);

    }


    private void vModelInit() {


        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mListViewModel.refresh();

        mListViewModel.bbChar.observe(getViewLifecycleOwner(), charList -> {

            mRecView.setVisibility(View.VISIBLE);
            mListAdapter.updateCharList(charList);

        });

        mListViewModel.loading.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean != null && aBoolean instanceof Boolean) {

                pBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);

                if (aBoolean) {

                    mRecView.setVisibility(View.GONE);

                }

            }

        });

    }

}
