package com.sammy.ona.trending;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sammy.ona.model.Organisation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.neverstoplearning.advancedandroid.R;

class OrganisationAdapter extends RecyclerView.Adapter<OrganisationAdapter.RepoViewHolder> {

    private final RepoClickedListener listener;
    private final List<Organisation> data = new ArrayList<>();

    OrganisationAdapter(RepoClickedListener listener) {
        this.listener = listener;
        setHasStableIds(true);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_organisation_list_item, parent, false);
        return new RepoViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


/*@Override
    public long getItemId(int position) {
        return data.get(position).id();
    }*/
    void setData(List<Organisation> repos) {
        if (repos != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new OrganisationDiffCallback(data, repos));
            data.clear();
            data.addAll(repos);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_organisation) TextView repoNameText;
        @BindView(R.id.tv_country) TextView repoDescriptionText;
        @BindView(R.id.tv_city) TextView forkCountText;
        @BindView(R.id.tv_date_modified) TextView starCountText;

        private Organisation organisation;

        RepoViewHolder(View itemView, RepoClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (organisation != null) {
                    listener.onRepoClicked(organisation);
                }
            });
        }

        void bind(Organisation organisation) {
            this.organisation = organisation;
            repoNameText.setText(organisation.organisation());
            repoDescriptionText.setText(organisation.country());
            forkCountText.setText(organisation.city());
            starCountText.setText(organisation.dateModified());
        }
    }

    interface RepoClickedListener {

        void onRepoClicked(Organisation repo);
    }
}
