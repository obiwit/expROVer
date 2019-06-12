package ua.deti.exprover;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import ua.deti.exprover.models.Bookmark;
import ua.deti.exprover.utils.BookmarkAdapter;


public class VideoPlaying extends AppCompatActivity implements AddBookmarkDialogFragment.NoticeDialogListener,
        EditBookmarkDialogFragment.EditBookmarkDialogListener,
        ConfirmDeleteDialogFragment.ConfirmDeleteBookmarkDialogListener {
    private List<Bookmark> bookmarkList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BookmarkAdapter mAdapter;

    private ExoPlayer player;
    private PlayerView playerView;
    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;

    private ImageView fullscreenImageView, addBookmarkImageView, previousBookmarkIV, nextBookmarkIV;
    private LinearLayout sidebar, backBtn;
    private int tempMil;
    private VideoPlaying videoPlaying;
    private int tempPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_playing);

        backBtn = findViewById(R.id.backBtn);
        playerView = findViewById(R.id.player_view);
        fullscreenImageView = findViewById(R.id.exo_fullscreen_icon);
        addBookmarkImageView = findViewById(R.id.exo_add_bookmark);
        previousBookmarkIV = findViewById(R.id.exo_previous_bookmark);
        nextBookmarkIV = findViewById(R.id.exo_next_bookmark);
        sidebar = findViewById(R.id.sidebar);
        videoPlaying = this;


        /* Recycle View for bookmarks */
        recyclerView = (RecyclerView) findViewById(R.id.bookmark_recycler_view);
        mAdapter = new BookmarkAdapter(this, bookmarkList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        initBookmarkData();


        fullscreenImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (sidebar.getVisibility() == LinearLayout.GONE) {
                    sidebar.setVisibility(LinearLayout.VISIBLE);
                    backBtn.setVisibility(LinearLayout.VISIBLE);
                } else {
                    sidebar.setVisibility(LinearLayout.GONE);
                    backBtn.setVisibility(LinearLayout.GONE);
                }
            }
        });

        addBookmarkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AddBookmarkDialogFragment addFragment = new AddBookmarkDialogFragment();

                tempMil = (int) (player.getCurrentPosition() / 1000);

                addFragment.show(getSupportFragmentManager(), "NoticeDialogFragment");
            }
        });

        previousBookmarkIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int tempNow = (int) (player.getCurrentPosition() / 1000);
                tempNow--;
                int i;

                for (i = bookmarkList.size() - 1; i >= 0 && bookmarkList.get(i).getTime() > tempNow; i--) {
                }
                if (i >= 0) {
                    player.seekTo(1000 * bookmarkList.get(i).getTime());
                } else {
                    player.seekTo(0);
                }
            }
        });

        nextBookmarkIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int tempNow = (int) (player.getCurrentPosition() / 1000);
                tempNow++;
                int i;

                for (i = 0; i <= bookmarkList.size() && bookmarkList.get(i).getTime() < tempNow; i++) {
                }
                System.out.println("i = " + i);
                if (i < bookmarkList.size()) {
                    player.seekTo(1000 * bookmarkList.get(i).getTime());
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                finish();
            }
        });
    }


    /* Boilerplate for Exoplayer */
    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }


    private void initializePlayer() {
        /* Boilerplate for Exoplayer */
        // Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        //Initialize the player
//        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        player = ExoPlayerFactory.newSimpleInstance(
                (this),
                new DefaultTrackSelector(), new DefaultLoadControl());


        //Initialize simpleExoPlayerView - Finally something comprehensible
        playerView.setPlayer(player);

        // Produces DataSource instances through which media data is loaded - and I'm lost again
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, Util.getUserAgent(this, "CloudinaryExoplayer"));

        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();


        /* Now check your seat belts, we're going on a ride
         * After requesting the permission on the manifest, we still need to
         * check every time if we have the permission, and if we don't have it...
         * well, just ask for it! */
        // check if we have the permission
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        // and request it if we don't
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    5);

        }

        /* Use this Uri to an online video */
        Uri videoUri = Uri.parse("http://10.42.0.1:8080/ozzz1.cgi");

        /* Or this one for a video located on /sdcard/Movies/test.mp4 */
//        File sdcard = Environment.getExternalStorageDirectory();
//        Uri videoUri = Uri.fromFile(new File(sdcard.getAbsolutePath() + "/Movies/test.mp4"));


        MediaSource videoSource = new ExtractorMediaSource(videoUri,
                dataSourceFactory, extractorsFactory, null, null);

        // Prepare the player with the source - more boilerplate
        player.prepare(videoSource);
        player.setPlayWhenReady(true);

        mAdapter.setPlayer(player);
        mAdapter.setVideoPlaying(videoPlaying);

    }

    /* Boilerplate */
    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }


    private void initBookmarkData() {
        Bookmark bookmark = new Bookmark(15, "No comments");
        bookmarkList.add(bookmark);

        bookmark = new Bookmark(325, "The big whales");
        bookmarkList.add(bookmark);

        bookmark = new Bookmark(569, "The little whale");
        bookmarkList.add(bookmark);

        bookmark = new Bookmark(800, "Shark");
        bookmarkList.add(bookmark);

        bookmark = new Bookmark(1253, "Fish tank");
        bookmarkList.add(bookmark);

        mAdapter.notifyDataSetChanged();
    }


    public void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    /* To Handle Dialog Events */

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new AddBookmarkDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String name) {
        // User touched the dialog's positive button
        addBookmark(tempMil, name);
        hideSystemUi();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        hideSystemUi();
    }

    @Override
    public void onEditDialogPositiveClick(DialogFragment dialog, String name) {
        // Edit
        editBookmark(tempPos, name);
        hideSystemUi();
    }

    @Override
    public void onEditDialogNegativeClick(DialogFragment dialog) {
        // Cancel
        hideSystemUi();
    }

    @Override
    public void onEditDialogNeutralClick(DialogFragment dialog) {
        // Delete
//        deleteBookmark(tempPos);

        showDeleteBookmarkDialog();
    }

    private void deleteBookmark(int pos) {
        bookmarkList.remove(pos);
        mAdapter.notifyDataSetChanged();
    }


    public void addBookmark(int time, String name) {
        Bookmark bookmark = new Bookmark(time, name);

        int i;

        for (i = 0; i < bookmarkList.size() && time > bookmarkList.get(i).getTime(); i++) {
        }
        bookmarkList.add(i, bookmark);

        mAdapter.notifyDataSetChanged();
    }

    public void editBookmark(int pos, String name) {
        Bookmark bookmark = bookmarkList.get(pos);

        bookmark.setTitle(name);

        bookmarkList.set(pos, bookmark);
        mAdapter.notifyDataSetChanged();
    }


    public void editBookmarkInterface(int position) {
        showEditBookmarkDialog(position);
    }

    public void deleteBookmarkInterface(int position) {
        showDeleteBookmarkDialog();
    }

    public void showDeleteBookmarkDialog() {
        ConfirmDeleteDialogFragment delFragment = new ConfirmDeleteDialogFragment();

        tempMil = (int) (player.getCurrentPosition() / 1000);

        delFragment.show(getSupportFragmentManager(), "DeleteDialogFragment");

        hideSystemUi();
    }


    public void showEditBookmarkDialog(int position) {
        tempPos = position;
        DialogFragment newFragment = new EditBookmarkDialogFragment();
        ((EditBookmarkDialogFragment) newFragment).setEditName(bookmarkList.get(position).getTitle());
        newFragment.show(getSupportFragmentManager(), "EditFragment");
    }

    public void onConfirmDeletePositiveClick(DialogFragment dialog){
        deleteBookmark(tempPos);
        hideSystemUi();
    }

    public void onConfirmDeleteNegativeClick(DialogFragment dialog){
        hideSystemUi();
    }


}
