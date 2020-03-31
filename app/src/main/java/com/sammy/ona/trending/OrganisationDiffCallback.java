package com.sammy.ona.trending;

import androidx.recyclerview.widget.DiffUtil;

import com.sammy.ona.model.Organisation;

import java.util.List;

public class OrganisationDiffCallback extends DiffUtil.Callback {

    private final List<Organisation> oldList;
    private final List<Organisation> newList;

    public OrganisationDiffCallback(List<Organisation> oldList, List<Organisation> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
