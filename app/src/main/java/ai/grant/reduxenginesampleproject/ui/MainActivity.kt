package ai.grant.reduxenginesampleproject.ui

import ai.grant.reduxengine.INSTANCE.listen
import ai.grant.reduxengine.extensions.addTo
import ai.grant.reduxenginesampleproject.R
import ai.grant.reduxenginesampleproject.extensions.dispose
import ai.grant.reduxenginesampleproject.extensions.instance
import ai.grant.reduxenginesampleproject.model.AppState
import ai.grant.reduxenginesampleproject.ui.components.BaseView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.viewSwapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val disposables: ArrayList<ReceiveChannel<AppState>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listen<AppState, String>({ it.currentView }) { currentView ->
            println(Thread.currentThread().name)
            viewSwapper.next(currentView.instance(this))
        }.addTo(disposables)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    override fun onBackPressed() {
        (viewSwapper[0] as BaseView).onBackPressed()
    }
}
