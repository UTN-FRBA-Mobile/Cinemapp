package com.utn.frba.cinemapp.domain.servicies

import android.graphics.Bitmap

interface ImagesDataStore {

    /**
     *
     */
    fun getImageAsync(
        imagePath: String,
        onSuccess: (Bitmap?) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}