package com.dinosys.sportbook.features.tournament

import com.dinosys.sportbook.networks.models.TournamentDataModel

interface OnTournamentListener{
    fun onTournamentClick(tournament: TournamentDataModel?)
}