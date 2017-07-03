package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

data class OpponentListModel(
        @SerializedName("my_groups")
        val myGroups: List<MyGroupModel>? = null,

        @SerializedName("other_groups")
        val otherGroups: List<OtherGroupModel>? = null
)

data class MyGroupModel(
        @SerializedName("group_name")
        val groupName: String? = null,
        @SerializedName("opponent_teams")
        val opponentTeams: List<OpponentTeamModel>? = null
)

data class OpponentTeamModel(
        @SerializedName("team_id")
        val teamId: Int? = null,

        @SerializedName("team_name")
        val teamName: String? = null,

        @SerializedName("invitation_id")
        val invitationId: Int? = null,

        @SerializedName("invitation_status")
        val invitationStatus: String? = null,

        @SerializedName("invitation_invitee_id")
        val invitationInviteeId: Int? = null,

        @SerializedName("invitation_inviter_id")
        val invitationInviterId: Int? = null
)

data class OtherGroupModel(
        @SerializedName("group_name")
        var groupName: String? = null,

        @SerializedName("teams")
        var teams: List<TeamModel>? = null
)
