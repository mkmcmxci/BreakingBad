package com.mkmcmxci.breakingbad.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.mkmcmxci.breakingbad.R;
import com.mkmcmxci.breakingbad.model.BCharacter;
import com.mkmcmxci.breakingbad.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    private ListViewModel mListViewModel;
    private ListAdapter mListAdapter;

    @BindView(R.id.fragment_list_recyclerview)
    RecyclerView recView;
    @BindView(R.id.fragment_list_progressBar)
    ProgressBar pBar;



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
        recView.setLayoutManager(new GridLayoutManager(getContext(),2));

        recView.setAdapter(mListAdapter);

    }


    private void vModelInit() {


        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mListViewModel.refresh();

        mListViewModel.bbChar.observe(getViewLifecycleOwner(), new Observer<List<BCharacter>>() {
            @Override
            public void onChanged(List<BCharacter> charList) {


                recView.setVisibility(View.VISIBLE);
                mListAdapter.updateCharList(charList);

            }
        });

        mListViewModel.loading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean != null && aBoolean instanceof Boolean) {
                    pBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                    if (aBoolean) {
                        recView.setVisibility(View.GONE);

                    }


                }
            }
        });


    }
}
