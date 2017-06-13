package com.dinosys.sportbook.features.mytournament

import com.dinosys.sportbook.networks.models.MyTournamentModel

class MyTournamentViewModel {

    open fun getMyTournamentList(): List<MyTournamentModel> {
        val myTournaments = ArrayList<MyTournamentModel>();
        for (i in 0..10) {
            val model = MyTournamentModel("MyTournament " + i,
                    "13/06/2016", "Registered", "13/06/2017",
                    "http://www.insidepoolmag.com/wp-content/uploads/worldstraight2.jpg")
            myTournaments.add(model)
        }

        return myTournaments
    }
}