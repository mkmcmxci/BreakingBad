package com.mkmcmxci.breakingbad.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.mkmcmxci.breakingbad.R;
import com.mkmcmxci.breakingbad.databinding.FragmentDetailBinding;
import com.mkmcmxci.breakingbad.model.BCharacter;
import com.mkmcmxci.breakingbad.util.GetArgs;
import com.mkmcmxci.breakingbad.viewmodel.DetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {


    FragmentDetailBinding binding;
    private DetailViewModel mListViewModel;
    View view;
    int id;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.textView2)
    TextView textView2;

    @BindView(R.id.textView3)
    TextView textView3;

    @BindView(R.id.textView4)
    TextView textView4;


    @BindView(R.id.fragment_detail_progresBar)
    ProgressBar pBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        view = binding.getRoot();

        ButterKnife.bind(this, view);


        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getArgs();
        isLayoutVisible();
        vModelInit();

    }



    private void vModelInit() {

        mListViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        mListViewModel.fetch(id);
        mListViewModel.singleChar.observe(getViewLifecycleOwner(), new Observer<BCharacter>() {
            @Override
            public void onChanged(BCharacter bCharacter) {

                binding.setCharacters(bCharacter);

            }
        });

        mListViewModel.loading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (aBoolean != null && aBoolean instanceof Boolean) {

                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);

                    pBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);


                }
            }
        });



    }

    private void isLayoutVisible(){
        textView.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
        textView4.setVisibility(View.GONE);
        pBar.setVisibility(View.VISIBLE);

    }

    private void getArgs() {

        id = getArguments().getInt(GetArgs.CHAR_ID);

    }
}
