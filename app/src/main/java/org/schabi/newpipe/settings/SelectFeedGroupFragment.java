package org.schabi.newpipe.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.schabi.newpipe.R;
import org.schabi.newpipe.database.feed.model.FeedGroupEntity;
import org.schabi.newpipe.error.ErrorUtil;
import org.schabi.newpipe.local.feed.FeedDatabaseManager;
import org.schabi.newpipe.local.subscription.FeedGroupIcon;
import org.schabi.newpipe.util.ThemeHelper;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SelectFeedGroupFragment extends DialogFragment {

    private OnSelectedListener onSelectedListener = null;
    private OnCancelListener onCancelListener = null;

    private List<FeedGroupEntity> feedGroups = new Vector<>();
    private FeedGroupEntity allSubscriptionsFeedGroup;

    public void setOnSelectedListener(final OnSelectedListener listener) {
        onSelectedListener = listener;
    }

    public void setOnCancelListener(final OnCancelListener listener) {
        onCancelListener = listener;
    }

    /*//////////////////////////////////////////////////////////////////////////
    // Init
    //////////////////////////////////////////////////////////////////////////*/

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, ThemeHelper.getMinWidthDialogTheme(requireContext()));
        allSubscriptionsFeedGroup =
                new FeedGroupEntity(FeedGroupEntity.GROUP_ALL_ID,
                        requireContext().getString(R.string.all),
                        FeedGroupIcon.RSS,
                        -1);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View v = inflater.inflate(
                R.layout.select_feed_group_fragment, container, false);
        final RecyclerView recyclerView = v.findViewById(R.id.items_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final SelectFeedGroupAdapter feedGroupAdapter = new SelectFeedGroupAdapter();
        recyclerView.setAdapter(feedGroupAdapter);

        final FeedDatabaseManager feedDatabaseManager = new FeedDatabaseManager(requireContext());
        feedDatabaseManager.groups().toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getFeedGroupsObserver());
        return v;
    }

    /*//////////////////////////////////////////////////////////////////////////
    // Handle actions
    //////////////////////////////////////////////////////////////////////////*/

    @Override
    public void onCancel(@NonNull final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (onCancelListener != null) {
            onCancelListener.onCancel();
        }
    }

    private void clickedItem(final int position) {
        if (onSelectedListener != null) {
            final FeedGroupEntity entry = feedGroups.get(position);
            onSelectedListener
                    .onFeedGroupSelected(entry.getUid(), entry.getName(), entry.getIcon());
        }
        dismiss();
    }

    /*//////////////////////////////////////////////////////////////////////////
    // Item handling
    //////////////////////////////////////////////////////////////////////////*/

    private void displayFeedGroups(final List<FeedGroupEntity> newFeedGroups) {
        this.feedGroups = Stream.concat(Stream.of(allSubscriptionsFeedGroup),
                        newFeedGroups.stream()).collect(Collectors.toList());
    }

    private Observer<List<FeedGroupEntity>> getFeedGroupsObserver() {
        return new Observer<List<FeedGroupEntity>>() {
            @Override
            public void onSubscribe(@NonNull final Disposable disposable) { }

            @Override
            public void onNext(@NonNull final List<FeedGroupEntity> newFeedGroups) {
                displayFeedGroups(newFeedGroups);
            }

            @Override
            public void onError(@NonNull final Throwable exception) {
                ErrorUtil.showUiErrorSnackbar(SelectFeedGroupFragment.this,
                        "Loading feed groups", exception);
            }

            @Override
            public void onComplete() { }
        };
    }

    /*//////////////////////////////////////////////////////////////////////////
    // Interfaces
    //////////////////////////////////////////////////////////////////////////*/

    public interface OnSelectedListener {
        void onFeedGroupSelected(long feedGroupId,
                                 String feedGroupName,
                                 FeedGroupIcon feedGroupIcon);
    }

    public interface OnCancelListener {
        void onCancel();
    }

    private class SelectFeedGroupAdapter
            extends RecyclerView.Adapter<SelectFeedGroupAdapter.SelectFeedGroupItemHolder> {
        @NonNull
        @Override
        public SelectFeedGroupItemHolder onCreateViewHolder(final ViewGroup parent,
                                                            final int viewType) {
            final View item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.select_feed_group_item, parent, false);
            return new SelectFeedGroupItemHolder(item);
        }

        @Override
        public void onBindViewHolder(final SelectFeedGroupItemHolder holder, final int position) {
            final FeedGroupEntity entry = feedGroups.get(position);
            holder.titleView.setText(entry.getName());
            holder.thumbnailView
                    .setImageDrawable(AppCompatResources.getDrawable(requireContext(),
                            entry.getIcon().getDrawableResource()));
            holder.view.setOnClickListener(view -> clickedItem(position));
        }

        @Override
        public int getItemCount() {
            return feedGroups.size();
        }

        public class SelectFeedGroupItemHolder extends RecyclerView.ViewHolder {
            public final View view;
            final ImageView thumbnailView;
            final TextView titleView;
            SelectFeedGroupItemHolder(final View v) {
                super(v);
                this.view = v;
                thumbnailView = v.findViewById(R.id.itemThumbnailView);
                titleView = v.findViewById(R.id.itemTitleView);
            }
        }
    }
}
