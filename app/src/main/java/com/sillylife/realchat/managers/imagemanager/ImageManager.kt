package com.sillylife.realchat.managers.imagemanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.signature.ObjectKey
import com.sillylife.realchat.RealChat
import com.sillylife.realchat.managers.imagemanager.svg.SvgSoftwareLayerSetter
import java.io.ByteArrayOutputStream
import java.util.concurrent.ExecutionException

object ImageManager {

    private val context = RealChat.getInstance()

    fun loadImage(imageView: ImageView, imageUrl: String?) {
        val url = imageUrl ?: ""
        load(
                imageView,
                url,
                0,
                getRequestOptions(imageView.drawable),
                null
        )
    }

    fun loadImageCircular(imageView: ImageView, imageUrl: String?) {
        val url = imageUrl ?: ""
        load(
                imageView,
                url,
                0,
                getRequestOptions(imageView.drawable),
                RequestOptions.circleCropTransform()
        )
    }

    fun loadImage(imageView: ImageView, resourceId: Int) {
        load(
                imageView,
                null,
                resourceId,
                getRequestOptions(resourceId),
                null
        )
    }

    fun loadImageCircular(imageView: ImageView, resourceId: Int) {
        load(
                imageView,
                null,
                resourceId,
                getRequestOptions(resourceId),
                RequestOptions.circleCropTransform()
        )
    }

    fun loadImage(imageView: ImageView, imageUrl: String?, placeHolderId: Int) {
        val url = imageUrl ?: ""
        load(
                imageView,
                url,
                placeHolderId,
                getRequestOptions(placeHolderId),
                null
        )
    }

    fun loadImageCircular(imageView: ImageView, imageUrl: String?, placeHolderId: Int) {
        val url = imageUrl ?: ""
        load(
                imageView,
                url,
                placeHolderId,
                getRequestOptions(placeHolderId),
                RequestOptions.circleCropTransform()
        )
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    fun getBitmapSync(url: String, width: Int, height: Int): Bitmap {
        val stream = ByteArrayOutputStream()
        val bitmap = Glide.with(context)
                .asBitmap()
                .load(url)
                .submit(width, height).get()
        return if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
            Glide.with(context).asBitmap().load(stream.toByteArray()).submit(width, height).get()
        } else {
            bitmap
        }
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    fun getBitmapSync(url: String): Bitmap {
        val stream = ByteArrayOutputStream()
        val bitmap = Glide.with(context)
                .asBitmap()
                .load(url)
                .submit().get()
        return if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
            Glide.with(context).asBitmap().load(stream.toByteArray()).submit().get()
        } else {
            bitmap
        }
    }

    fun loadSVGImage(imageView: ImageView, imageUrl: String?) {
        val url = imageUrl ?: ""
        Glide.with(context).clear(imageView)
        Glide.with(context)
                .setDefaultRequestOptions(getRequestOptions(imageView.drawable))
                .`as`(PictureDrawable::class.java)
                .listener(SvgSoftwareLayerSetter())
                .load(url)
                .into(imageView)
    }

    fun loadSVGImageCircular(imageView: ImageView, imageUrl: String?) {
        val url = imageUrl ?: ""
        Glide.with(context).clear(imageView)
        Glide.with(context)
                .setDefaultRequestOptions(getRequestOptions(imageView.drawable))
                .`as`(PictureDrawable::class.java)
                .listener(SvgSoftwareLayerSetter())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView)
    }

    fun loadImage(url: String, width: Int, height: Int, bitmapSimpleTarget: SimpleTarget<Bitmap>) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(getDefaultRequestOptions(context).override(width, height))
                .into(bitmapSimpleTarget)
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    fun getBitmapSync(url: String, context: Context): Bitmap {
        val stream = ByteArrayOutputStream()
        val bitmap = Glide.with(context)
                .asBitmap()
                .load(url)
                .submit().get()
        return if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
            Glide.with(context).asBitmap().load(stream.toByteArray()).submit().get()
        } else {
            bitmap
        }
    }

    private fun getDefaultRequestOptions(context: Context): RequestOptions {
        var requestOptions = RequestOptions()
        val progressDrawable = CircularProgressDrawable(context)
        progressDrawable.strokeWidth = 5f
        progressDrawable.centerRadius = 8f
        progressDrawable.start()
        requestOptions = requestOptions.placeholder(progressDrawable)
        //requestOptions = requestOptions.error(R.drawable.ku)
        return requestOptions
    }

    private fun getRequestOptions(drawable: Drawable?): RequestOptions {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.placeholder(drawable)
        requestOptions = requestOptions.error(drawable)
        return requestOptions
    }

    private fun getRequestOptions(placeHolderId: Int): RequestOptions {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.placeholder(placeHolderId)
        requestOptions = requestOptions.error(placeHolderId)
        return requestOptions
    }

    private fun load(imageView: ImageView, imageUrl: String?, placeHolderId: Int,
                     defaultRequestOptions: RequestOptions, transformationRequestOptions: RequestOptions?) {

        Glide.with(context).clear(imageView)
        var requestManager = Glide.with(context)
        requestManager = requestManager.setDefaultRequestOptions(defaultRequestOptions)
        var requestBuilder: RequestBuilder<Drawable>? = null
        var signature: ObjectKey? = null
        if (!TextUtils.isEmpty(imageUrl)) {
            requestBuilder = requestManager.load(imageUrl)
            signature = ObjectKey(imageUrl as Any)
        }
        if (placeHolderId > 0) {
            requestBuilder = requestManager.load(placeHolderId)
            signature = ObjectKey(placeHolderId as Any)
        }
        if (requestBuilder != null) {
            if (transformationRequestOptions != null) {
                requestBuilder = requestBuilder.apply(transformationRequestOptions)
            }
            requestBuilder.signature(signature!!).into(imageView)
        }

    }

}