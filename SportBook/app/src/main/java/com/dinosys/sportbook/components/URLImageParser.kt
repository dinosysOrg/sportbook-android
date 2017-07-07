package com.dinosys.sportbook.components

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.AsyncTask
import android.support.v4.content.ContextCompat
import android.text.Html.ImageGetter
import android.widget.TextView
import com.dinosys.sportbook.R
import com.bumptech.glide.Glide
import com.dinosys.sportbook.utils.ScreenUtil

class URLImageParser(val context: Context, val container: TextView) : ImageGetter {

    override fun getDrawable(source: String): Drawable {
        val levelListDrawable = LevelListDrawable()
        val empty = ContextCompat.getDrawable(context, R.mipmap.ic_launcher)
        levelListDrawable.addLevel(0, 0, empty)
        levelListDrawable.setBounds(0, 0, empty.intrinsicWidth, empty.intrinsicHeight)
        LoadImage(context, container).execute(source, levelListDrawable)
        return levelListDrawable
    }

}

class LoadImage(var context: Context?, var container: TextView?) : AsyncTask<Any, Void, Bitmap>() {

    private var mDrawable: LevelListDrawable? = null

    override fun doInBackground(vararg params: Any): Bitmap? {
        val source = params[0] as String
        mDrawable = params[1] as LevelListDrawable
        return Glide.with(context).load(source).asBitmap().into(100, 100).get()
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        var bitmap = bitmap
        if (bitmap != null) {
            val screenWidth = ScreenUtil.getScreenInfo(context as Activity).x - 100

            val newHeight = screenWidth / 1.77
            if (bitmap.height > bitmap.width) {
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        newHeight.toInt(), screenWidth, false)
            } else {
                bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth,
                        newHeight.toInt(), false)
            }

            val drawable = BitmapDrawable((context as Activity).resources, bitmap)
            mDrawable!!.addLevel(1, 1, drawable)
            mDrawable!!.setBounds(0, 0, bitmap!!.width, bitmap.height)
            mDrawable!!.level = 1
            // i don't know yet a better way to refresh TextView
            // mTv.invalidate() doesn't work as expected
            val text = container!!.text
            container!!.text = text
        }

        container = null
        context = null
    }
}
