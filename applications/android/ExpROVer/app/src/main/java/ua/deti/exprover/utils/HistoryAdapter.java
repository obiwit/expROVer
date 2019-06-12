package ua.deti.exprover.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ua.deti.exprover.R;
import ua.deti.exprover.VideoPlaying;
import ua.deti.exprover.models.History;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> historyList;
    private Context context;

    public HistoryAdapter(Context context, List<History> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout._history_item, parent, false);

        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.title.setText(historyList.get(position).getTitle());
        holder.date.setText(historyList.get(position).getDate().toString());
        holder.duration.setText(String.valueOf(historyList.get(position).getDurationString()));
        holder.bookmarks.setText(String.valueOf(historyList.get(position).getBookmarks() + "⭐"));
        holder.location.setText("\uD83D\uDCCC " + historyList.get(position).getLocation());

        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.drawable.list_soft_bg_light);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.list_soft_bg_lighter);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoIntent = new Intent(context, VideoPlaying.class);
                context.startActivity(videoIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;
        public TextView duration;
        public TextView bookmarks;
        public TextView location;

        public HistoryViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.history_item_name);
            date = (TextView) view.findViewById(R.id.history_item_date);
            duration = (TextView) view.findViewById(R.id.history_item_duration);
            bookmarks = (TextView) view.findViewById(R.id.history_item_bookmarks);
            location = (TextView) view.findViewById(R.id.history_item_location);
        }
    }
}