package com.mkmcmxci.breakingbad.view;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

    @BindView(R.id.fragment_detail_portrayed_title)
    TextView portrayedTitle;

    @BindView(R.id.fragment_detail_nickname_title)
    TextView nicknameTitle;

    @BindView(R.id.fragment_detail_status_title)
    TextView statusTitle;

    @BindView(R.id.fragment_detail_birthday_title)
    TextView birthdayTitle;

    @BindView(R.id.fragment_detail_constraint_layout_two)
    ConstraintLayout constraintLayout;

    @BindView(R.id.fragment_detail_go_quotes)
    TextView goQuotes;

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
        mListViewModel.fetch(id, null);

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

                    portrayedTitle.setVisibility(View.VISIBLE);
                    nicknameTitle.setVisibility(View.VISIBLE);
                    statusTitle.setVisibility(View.VISIBLE);
                    birthdayTitle.setVisibility(View.VISIBLE);
                    constraintLayout.setVisibility(View.VISIBLE);
                    goQuotes.setVisibility(View.VISIBLE);

                    pBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);

                }

            }

        });


    }

    private void isLayoutVisible() {

        portrayedTitle.setVisibility(View.GONE);
        nicknameTitle.setVisibility(View.GONE);
        statusTitle.setVisibility(View.GONE);
        birthdayTitle.setVisibility(View.GONE);
        constraintLayout.setVisibility(View.GONE);
        goQuotes.setVisibility(View.GONE);

        pBar.setVisibility(View.VISIBLE);

    }

    private void getArgs() {

        id = getArguments().getInt(GetArgs.CHAR_ID);

    }

}
