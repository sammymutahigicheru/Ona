package com.sammy.ona.details;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sammy.ona.model.OrganisationUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.neverstoplearning.advancedandroid.R;

class OrganisationDetailsAdapter extends RecyclerView.Adapter<OrganisationDetailsAdapter.organisationUserViewHolder> {

    private final List<OrganisationUser> data = new ArrayList<>();

    OrganisationDetailsAdapter() {
        setHasStableIds(true);
    }

    @Override
    public organisationUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_list_item, parent, false);
        return new organisationUserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(organisationUserViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    void setData(List<OrganisationUser> organisationUsers) {
        if (organisationUsers != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new OrganisationDetailsDiffCallback(data, organisationUsers));
            data.clear();
            data.addAll(organisationUsers);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class organisationUserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_organisation_name) TextView usernameText;
        @BindView(R.id.iv_avatar) ImageView avatarImageView;

        organisationUserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(OrganisationUser contributor) {
            usernameText.setText(contributor.username());
            Glide.with(avatarImageView.getContext())
                    .load(contributor.gravatar())
                    .into(avatarImageView);
        }
    }
}
