package com.kevintcoughlin.smodr.viewholders;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.kevintcoughlin.common.adapter.BinderRecyclerAdapter;
import com.kevintcoughlin.smodr.R;
import com.kevintcoughlin.smodr.models.Item;
import com.kevintcoughlin.smodr.utils.StringResourceUtilities;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.Date;

public class EpisodeView implements BinderRecyclerAdapter.Binder<Item, EpisodeViewHolder> {
    private WeakReference<BinderRecyclerAdapter.OnClick<Item>> mOnClickListener;
    @SuppressLint("NewApi")
    private static final SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    @SuppressLint("NewApi")
    private static final SimpleDateFormat format2 = new SimpleDateFormat("dd MMM");

    @SuppressLint("NewApi")
    private static String formatDate(String dateTimeString) {
        // @todo: optimize
        Date date = null;
        String dateString = "";
        try {
            date = format.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            dateString = format2.format(date);
        }

        return dateString;
    }

    public EpisodeView(@NonNull final BinderRecyclerAdapter.OnClick<Item> onClick) {
        this.mOnClickListener = new WeakReference<>(onClick);
    }

    @Override
    public void bind(@NonNull final Item model, @NonNull final EpisodeViewHolder viewHolder) {
        viewHolder.mTitle.setText(model.title);
        viewHolder.mDescription.setText(Html.fromHtml(model.summary));
        viewHolder.mMetadata.setText(StringResourceUtilities.getString(viewHolder.mMetadata.getContext(), R.string.metadata, formatDate(model.pubDate), model.duration));
        viewHolder.itemView.setOnClickListener(v -> this.mOnClickListener.get().onClick(model));
    }

    @Override
    public EpisodeViewHolder createViewHolder(@NonNull final ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_episode_layout, parent, false);
        return new EpisodeViewHolder(view);
    }
}