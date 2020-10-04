package com.mkmcmxci.breakingbad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.breakingbad.R;
import com.mkmcmxci.breakingbad.databinding.RowFragmentListBinding;
import com.mkmcmxci.breakingbad.model.BCharacter;
import com.mkmcmxci.breakingbad.util.GetArgs;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CharViewHolder> implements CharClickListener {

    private ArrayList<BCharacter> charList;

    public ListAdapter(ArrayList<BCharacter> charList) {
        this.charList = charList;
    }

    public void updateCharList(List<BCharacter> newCharList) {

        charList.clear();
        charList.addAll(newCharList);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CharViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowFragmentListBinding view = DataBindingUtil.inflate(inflater, R.layout.row_fragment_list, parent, false);
        return new CharViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CharViewHolder holder, int position) {

        holder.itemBind.setCharacters(charList.get(position));
        holder.itemBind.setListener(this);

    }

    @Override
    public int getItemCount() {
        return charList.size();
    }

    @Override
    public void onCharClicked(View view) {

        String id = ((TextView) view.findViewById(R.id.char_id)).getText().toString();
        int newId = Integer.parseInt(id);
        Bundle bundle = new Bundle();
        bundle.putInt(GetArgs.CHAR_ID, newId);
        Navigation.findNavController(view).navigate(R.id.list_to_detail, bundle);

    }

    class CharViewHolder extends RecyclerView.ViewHolder {

        public RowFragmentListBinding itemBind;

        public CharViewHolder(@NonNull RowFragmentListBinding itemBind) {

            super(itemBind.getRoot());

            this.itemBind = itemBind;
        }

    }

}
