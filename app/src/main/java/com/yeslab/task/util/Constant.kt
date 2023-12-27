package com.yeslab.task.util

import android.app.Dialog
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yeslab.task.R
import com.yeslab.task.databinding.DialogMovieLayoutBinding

const val TAG = "Roshan"

fun ImageView.loadImage(
    context: Context,
    url: String,
    placeholder: Int? = null,
    errorPlaceholder: Int? = null
) {
    val options = RequestOptions().apply {
        placeholder?.let { placeholder(it) }
        errorPlaceholder?.let { error(it) }
    }

    Glide.with(context)
        .load(url)
        .apply(options)
        .into(this)
}


fun getDialog(context: Context, layoutId: Int): Dialog {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setBackgroundDrawableResource(R.color.trans)
    val layoutInflater = LayoutInflater.from(context)
    val view = layoutInflater.inflate(layoutId, null)
    dialog.setContentView(view)
    val width = getScreenWidth(context)
    val params = view.layoutParams
    params.width = width - (width * 13 / 100)
    view.layoutParams = params

    return dialog
}


fun getScreenWidth(context: Context): Int {
    val displayMetrics = DisplayMetrics()
    (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(
        displayMetrics
    )
    return displayMetrics.widthPixels
}