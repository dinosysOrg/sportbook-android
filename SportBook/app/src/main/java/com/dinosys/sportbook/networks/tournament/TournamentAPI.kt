package com.dinosys.sportbook.networks.tournament

import com.dinosys.sportbook.networks.models.TournamentModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header


interface TournamentAPI {

    @GET("tournaments")
    fun getTournaments(@Header("Access-Token") accessToken: String?,
                @Header("Client") client: String?,
                @Header("Expiry") expiry: String?,
                @Header("Token-Type") tokenType: String?,
                @Header("Uid") Uid: String?): Observable<Response<TournamentModel>>

}