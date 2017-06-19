package com.dinosys.sportbook.features.mytournament.opponent

import com.dinosys.sportbook.networks.models.OpponentModel


class OpponentViewModel(){

    val sampleOpponents: ArrayList<OpponentModel>
        get() {
            val items = ArrayList<OpponentModel>()
            items.add(OpponentModel("Nguen A","4 points","Win","Swipe to schedule","https://dcassetcdn.com/common/images/v3/no-profile-pic-tiny.png"))
            items.add(OpponentModel("Nguen B","5 points","Win","Swipe to schedule","https://dcassetcdn.com/common/images/v3/no-profile-pic-tiny.png"))
            items.add(OpponentModel("Tran Van C","3 points","Win","Swipe to schedule","https://dcassetcdn.com/common/images/v3/no-profile-pic-tiny.png"))
            items.add(OpponentModel("Truong A","2 points","Win","Swipe to schedule","https://dcassetcdn.com/common/images/v3/no-profile-pic-tiny.png"))

            return items
        }
}