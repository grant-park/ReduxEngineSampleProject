package ai.grant.reduxenginesampleproject.extensions

import android.content.Context
import android.view.View

fun String.instance(context: Context): View =
    Class.forName(this).getConstructor(Context::class.java).newInstance(context) as View
