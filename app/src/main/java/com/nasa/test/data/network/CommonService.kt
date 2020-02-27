package com.nasa.test.data.network

import com.nasa.test.data.model.response.MarsPhotoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommonService {

    @GET("mars-photos/api/v1/rovers/{roverName}/photos")
    fun getMarsRoversPhotos(
        @Path("roverName") roverName: String?, @Query("sol") sol: Int, @Query("camera") cameraName: String?
    ): Single<MarsPhotoResponse?>
}