package com.kevintcoughlin.smodr.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.kevintcoughlin.smodr.R;
import com.kevintcoughlin.smodr.models.Channel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public final class ChannelsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private final ArrayList<Channel> mItems = new ArrayList<>();

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.channels_grid_item_layout, parent, false);
		return new ChannelViewHolder(v);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		final Channel channel = mItems.get(position);
		final ChannelViewHolder vh = (ChannelViewHolder) holder;

		Picasso.with(holder.itemView.getContext())
				.load("http://i1.sndcdn.com/avatars-000069229441-16gxj6-original.jpg")
				.resize(350, 350)
				.centerCrop()
				.into(vh.mImage);
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}

	public void setResults(final ArrayList<Channel> results) {
		if (!mItems.isEmpty()) {
			mItems.clear();
		}
		mItems.addAll(results);
	}

	public static class ChannelViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.image) ImageView mImage;

		public ChannelViewHolder(View itemView) {
			super(itemView);
			ButterKnife.inject(this, itemView);
		}
	}
}