package com.dinosys.sportbook.features.tournament

import com.dinosys.sportbook.networks.models.TournamentModel
import io.reactivex.Observable
import retrofit2.Response


class TournamentViewModel {

    fun getTournamentList() : List<TournamentModel> {
        val tournaments = ArrayList<TournamentModel>()
        for (i in 0 until 10) {
            val model = TournamentModel("My Tournament",
                    "12-16/07/17",
                    "10-11/07/17",
                    "https://images.cdn.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/images/car-reviews/first-drives/legacy/renders-17th-896.jpg?itok=4mprPLvZ"
            )
            tournaments.add(model)
        }
        return tournaments
    }
}