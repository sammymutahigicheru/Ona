package com.sammy.ona.details;

import androidx.recyclerview.widget.DiffUtil;

import com.sammy.ona.model.OrganisationUser;

import java.util.List;

public class OrganisationDetailsDiffCallback extends DiffUtil.Callback {

    private final List<OrganisationUser> oldList;
    private final List<OrganisationUser> newList;

    OrganisationDetailsDiffCallback(List<OrganisationUser> oldList, List<OrganisationUser> newList) {
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
        return oldList.get(oldItemPosition)== newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
