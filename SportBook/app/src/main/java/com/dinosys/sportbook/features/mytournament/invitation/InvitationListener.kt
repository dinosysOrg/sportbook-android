package com.dinosys.sportbook.features.mytournament.invitation

import com.dinosys.sportbook.networks.models.OpponentTeamModel


interface InvitationListener {
    fun onDaySelected(day: Int)
}