package ai.grant.reduxenginesampleproject.ui.screens

import ai.grant.reduxenginesampleproject.R
import ai.grant.reduxenginesampleproject.common.extensions.dispose
import android.content.Context
import android.transition.Slide
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.transition.doOnEnd
import kotlinx.android.synthetic.main.dialog.view.dialog
import kotlinx.android.synthetic.main.dialog.view.leftButton
import kotlinx.android.synthetic.main.dialog.view.message
import kotlinx.android.synthetic.main.dialog.view.rightButton
import kotlinx.coroutines.channels.ReceiveChannel

abstract class BaseView(private val layoutResId: Int, context: Context) : FrameLayout(context) {
    protected val disposables = mutableListOf<ReceiveChannel<*>>()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addView(LayoutInflater.from(context).inflate(layoutResId, null))
    }

    override fun onDetachedFromWindow() {
        disposables.dispose()
        super.onDetachedFromWindow()
    }

    fun onBackPressed() {
        // no op
    }

    fun toggleSoftInput(toggle: Boolean) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (toggle) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        } else {
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
    }

    fun showDialog(
        message: String,
        leftButtonText: String,
        rightButtonText: String,
        isLeftButtonSelectedCallback: (Boolean) -> Unit
    ) {
        val confirmDialog = LayoutInflater.from(context).inflate(R.layout.dialog, null)
        val callbackAfterTransition = { isLeftSelected: Boolean ->
            val transition = Slide().apply { doOnEnd { isLeftButtonSelectedCallback(isLeftSelected) } }
            TransitionManager.beginDelayedTransition(this, transition)
            removeView(confirmDialog)
        }
        confirmDialog.message.text = message
        confirmDialog.leftButton.text = leftButtonText
        confirmDialog.rightButton.text = rightButtonText
        confirmDialog.dialog.leftButton.setOnClickListener { callbackAfterTransition(true) }
        confirmDialog.dialog.rightButton.setOnClickListener { callbackAfterTransition(false) }

        TransitionManager.beginDelayedTransition(this, Slide())
        addView(confirmDialog)
    }
}
