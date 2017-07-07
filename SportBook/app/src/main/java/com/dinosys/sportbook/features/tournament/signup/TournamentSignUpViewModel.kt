package com.dinosys.sportbook.features.tournament.signup

import android.content.Context
import com.dinosys.sportbook.extensions.readJsonFromAsset
import com.dinosys.sportbook.networks.models.SkillModel
import com.dinosys.sportbook.networks.models.TournamentSignUpModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TournamentSignUpViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getSkills() : Observable<Response<SkillModel>> {
        return tournamentAPI.getSkills()
    }

    fun signUpTournament(idTournament: Int,
                         name: String,
                         birthday: String,
                         phoneNumber: String,
                         address: String,
                         club: String,
                         skillId: Int): Observable<Response<TournamentSignUpModel>>  {

        return tournamentAPI.signUpTournament(idTournament = idTournament, name = name,
                birthday = birthday, phoneNumber = phoneNumber, address = address, club = club, skillId = skillId)
    }

    fun getCities(context: Context): Observable<JSONArray>  = Observable.create<JSONArray> {
            e ->
            try {
                val jsonText = String.readJsonFromAsset(context, "list_of_cities.json")
                val jsonArray = JSONArray(jsonText)
                e.onNext(jsonArray)
                e.onComplete()
            } catch (error: IOException) {
                e.onError(error)
            } catch (error: JSONException) {
                e.onError(error)
            }
    }
}