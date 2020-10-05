package com.mkmcmxci.breakingbad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.breakingbad.R;
import com.mkmcmxci.breakingbad.util.GetArgs;
import com.mkmcmxci.breakingbad.viewmodel.QuoteViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuoteFragment extends Fragment {

    public QuoteViewModel mViewModel;
    private QuoteAdapter mAdapter;
    private String name;

    @BindView(R.id.fragment_quote_recyclerView)
    RecyclerView mRecView;
    @BindView(R.id.fragment_quote_progressBar)
    ProgressBar pBar;
    @BindView(R.id.fragment_quote_no_quotes)
    TextView noQuotes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getArgs();
        recInit();
        noQuotes.setVisibility(View.GONE);
        vModelInit();

    }

    private void recInit() {

        mAdapter = new QuoteAdapter(new ArrayList<>());
        mRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecView.setAdapter(mAdapter);

    }

    private void vModelInit() {


        mViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        mViewModel.fetch(name);

        mViewModel.quote.observe(getViewLifecycleOwner(), quoteList -> {

            if(quoteList.size() != 0){

                mAdapter.updateQuoteList(quoteList);
                mRecView.setVisibility(View.VISIBLE);
                noQuotes.setVisibility(View.GONE);
            }
            else{

                mAdapter.updateQuoteList(quoteList);
                noQuotes.setVisibility(View.VISIBLE);

            }

        });

        mViewModel.loading.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean != null && aBoolean instanceof Boolean) {

                pBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);

                if (aBoolean) {

                    mRecView.setVisibility(View.GONE);
                    noQuotes.setVisibility(View.VISIBLE);

                }

            }

        });

    }

    private void getArgs() {

        name = getArguments().getString(GetArgs.CHAR_NAME);

    }
}
