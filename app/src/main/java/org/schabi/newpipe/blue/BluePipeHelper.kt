package org.schabi.newpipe.blue

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import org.schabi.newpipe.App
import org.schabi.newpipe.extractor.ServiceList.YouTube
import org.schabi.newpipe.util.ServiceHelper

object BluePipeHelper {

    private val handler = Handler(Looper.getMainLooper())

    /**
     * Returns true if the current service selected is YouTube.
     */
    @JvmStatic
    val isYouTube: Boolean
        get() = ServiceHelper.getSelectedServiceId(App.getApp()) == YouTube.serviceId

    /**
     * Simple toast, mainly used for debugging.
     *
     * Safe to call on any thread.
     */
    @JvmStatic
    @JvmOverloads
    fun toast(text: CharSequence, isLong: Boolean = false) {
        handler.post {
            Toast.makeText(App.getApp(), text, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
        }
    }
}
