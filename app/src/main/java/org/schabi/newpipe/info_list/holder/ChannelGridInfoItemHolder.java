package org.schabi.newpipe.info_list.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import org.schabi.newpipe.R;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.channel.ChannelInfoItem;
import org.schabi.newpipe.info_list.InfoItemBuilder;
import org.schabi.newpipe.local.history.HistoryRecordManager;
import org.schabi.newpipe.util.Localization;

public class ChannelGridInfoItemHolder extends ChannelMiniInfoItemHolder {
    private final TextView itemMoreAdditionalDetails;

    public ChannelGridInfoItemHolder(final InfoItemBuilder infoItemBuilder,
                                     final ViewGroup parent) {
        super(infoItemBuilder, R.layout.list_channel_grid_item, parent);

        itemMoreAdditionalDetails = itemView.findViewById(R.id.itemMoreAdditionalDetails);
    }

    @Override
    public void updateFromItem(final InfoItem infoItem,
                               final HistoryRecordManager historyRecordManager) {
        super.updateFromItem(infoItem, historyRecordManager);
        if (!(infoItem instanceof ChannelInfoItem)) {
            return;
        }
        final ChannelInfoItem item = (ChannelInfoItem) infoItem;

        itemMoreAdditionalDetails.setText(getMoreDetailsLine(item));
    }

    protected String getMoreDetailsLine(final ChannelInfoItem item) {
        String details = "";
        if (item.getStreamCount() >= 0) {
            details += "• " + Localization.localizeStreamCount(itemBuilder.getContext(),
                    item.getStreamCount()) + " ";
        }
        if (item.getDescription() != null) {
            details += "• " + item.getDescription();
        }
        return details;
    }
}
