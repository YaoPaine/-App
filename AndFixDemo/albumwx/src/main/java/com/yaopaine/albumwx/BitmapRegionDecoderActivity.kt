package com.yaopaine.albumwx

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bitmap_region_decoder.*

class BitmapRegionDecoderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_region_decoder)

        val inputStream = assets.open("image/tangyan.jpg")

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(inputStream, null, options)

        val myWidth = options.outWidth
        val height = options.outHeight

        options.inSampleSize = 1
        options.inJustDecodeBounds = false
        inputStream.reset()//两次调用decodeStream会返回null，需要把inputStream reset
        val bitmap1 = BitmapFactory.decodeStream(inputStream, null, options)

        val bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
        val options1 = BitmapFactory.Options()
        options1.inPreferredConfig = Bitmap.Config.ARGB_4444
        val bitmap = bitmapRegionDecoder.decodeRegion(Rect(myWidth / 2 - 100, height / 2 - 100, myWidth / 2 + 100, height / 2 + 100)
                , options1)

        inputStream.close()
//        inputStream2.close()

        iv1.setImageBitmap(bitmap)

        iv2.setImageBitmap(bitmap1)
    }

}
