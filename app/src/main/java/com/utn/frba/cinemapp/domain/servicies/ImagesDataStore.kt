package com.utn.frba.cinemapp.domain.servicies

import android.graphics.Bitmap

interface ImagesDataStore {

    fun getImage(imagePath: String): Bitmap?
}