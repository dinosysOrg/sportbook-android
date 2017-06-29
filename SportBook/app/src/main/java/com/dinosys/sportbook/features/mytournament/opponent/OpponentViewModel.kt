package com.dinosys.sportbook.features.mytournament.opponent

import com.dinosys.sportbook.networks.models.OpponentModel

class OpponentViewModel() {

    val sampleOpponents: ArrayList<OpponentModel>
        get() {
            val items = ArrayList<OpponentModel>()

            items.add(OpponentModel(1, "Nguen A"))
            items.add(OpponentModel(2, "Nguen A"))
            items.add(OpponentModel(3, "Nguen A"))
            items.add(OpponentModel(4, "Nguen A"))
            items.add(OpponentModel(5, "Nguen A"))

            return items
        }
}