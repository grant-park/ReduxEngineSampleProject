package ai.grant.reduxenginesampleproject.ui.utils

import android.os.SystemClock
import android.view.View

class DebouncedListener(private val action: () -> Unit) : View.OnClickListener {
    private var lastClickTime: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 600L) return
        action()
        lastClickTime = SystemClock.elapsedRealtime()
    }
}

