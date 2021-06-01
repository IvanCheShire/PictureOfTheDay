package geekbrains.material.mvp.model.repo

import geekbrains.material.mvp.model.entity.EarthPhotoData
import geekbrains.material.mvp.model.entity.EarthPhotoServerResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Call

interface IEarthGalleryRepo {
    fun getLastEarthPictures(): Single<EarthPhotoData>
    fun getEarthPhoto(earthPhotoServerResponse: EarthPhotoServerResponse): Call<ResponseBody>
}