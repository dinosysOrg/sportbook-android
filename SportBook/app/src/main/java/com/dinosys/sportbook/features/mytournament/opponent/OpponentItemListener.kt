package com.dinosys.sportbook.features.mytournament.opponent

import com.dinosys.sportbook.networks.models.OpponentTeamModel

interface OpponentItemListener {
    fun onOpponetItemSelected(opponentTeamModel: OpponentTeamModel)
}