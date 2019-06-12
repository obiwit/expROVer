package ua.deti.exprover.utils;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.google.android.exoplayer2.ExoPlayer;

import java.util.List;

import ua.deti.exprover.R;
import ua.deti.exprover.VideoPlaying;
import ua.deti.exprover.models.Bookmark;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private List<Bookmark> bookmarkList;
    private Context context;
    private ExoPlayer player;
    private VideoPlaying videoPlaying;

    public BookmarkAdapter(Context context, List<Bookmark> bookmarkList) {
        this.context = context;
        this.bookmarkList = bookmarkList;
    }

    @Override
    public BookmarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout._bookmark_item, parent, false);

        return new BookmarkViewHolder(itemView);
    }

    public void setPlayer(ExoPlayer player) {
        this.player = player;
    }

    public void setVideoPlaying(VideoPlaying videoPlaying) {
        this.videoPlaying = videoPlaying;
    }

    @Override
    public void onBindViewHolder(BookmarkViewHolder holder, final int position) {

        holder.title.setText(bookmarkList.get(position).getTitle());
        holder.time.setText(String.valueOf(bookmarkList.get(position).getDurationString()));
        final int p = position;

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlaying.editBookmarkInterface(p);
            }
        });

        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlaying.deleteBookmarkInterface(p);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.seekTo(1000 * bookmarkList.get(p).getTime());
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView time;
        public ImageView editButton, delButton;

        public BookmarkViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.bookmark_item_name);
            time = view.findViewById(R.id.bookmark_item_time);
            editButton = view.findViewById(R.id.bookmark_edit_button);
            delButton = view.findViewById(R.id.bookmark_delete_button);
        }
    }


}