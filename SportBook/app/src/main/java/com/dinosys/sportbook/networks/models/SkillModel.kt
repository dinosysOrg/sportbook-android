package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

data class SkillModel(@SerializedName("_embedded") val embedded: SkillEmbeddedModel?)

data class SkillEmbeddedModel(@SerializedName("skills") val skills: ArrayList<SkillDataModel>?)

data class SkillDataModel(@SerializedName("id") val id: Int?, @SerializedName("name") val name: String?)