package com.mkmcmxci.breakingbad.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.breakingbad.R;
import com.mkmcmxci.breakingbad.databinding.FragmentQuoteBinding;
import com.mkmcmxci.breakingbad.databinding.RowFragmentListBinding;
import com.mkmcmxci.breakingbad.databinding.RowFragmentQuoteBinding;
import com.mkmcmxci.breakingbad.model.BCharacter;
import com.mkmcmxci.breakingbad.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private ArrayList<Quote> quoteList;

    public QuoteAdapter(ArrayList<Quote> quoteList) {
        this.quoteList = quoteList;
    }

    public void updateQuoteList(List<Quote> newQuoteList) {

        quoteList.clear();
        quoteList.addAll(newQuoteList);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowFragmentQuoteBinding view = DataBindingUtil.inflate(inflater, R.layout.row_fragment_quote, parent, false);
        return new QuoteAdapter.QuoteViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {

        holder.itemBind.setQuotes(quoteList.get(position));

    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }



    class QuoteViewHolder extends RecyclerView.ViewHolder {

        public RowFragmentQuoteBinding itemBind;

        public QuoteViewHolder(@NonNull RowFragmentQuoteBinding itemBind) {

            super(itemBind.getRoot());

            this.itemBind = itemBind;
        }

    }
}
