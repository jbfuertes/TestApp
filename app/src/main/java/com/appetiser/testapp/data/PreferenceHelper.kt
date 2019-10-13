package com.appetiser.testapp.data

import android.content.Context
import androidx.core.content.edit
import com.appetiser.testapp.presenter.model.Track
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceHelper
@Inject
constructor(context: Context){

    private val sharedPreferences = context.getSharedPreferences("pref-v1", Context.MODE_PRIVATE)

    /**
     * Cache/Invalidate selected Track
     */
    fun setTrack(track: Track?){
        if (track == null){
            sharedPreferences.edit {
                remove("track")
            }
        }else {
            sharedPreferences.edit {
                putString("track", Json.stringify(Track.serializer(),track))
            }
        }
    }

    /**
     * Retrieves Track
     */
    fun getTrack():Track? {
        val json = sharedPreferences.getString("track", "")!!
        if (json.isEmpty()) {
            return null
        }
        return Json.parse(Track.serializer(), json)
    }

    /**
     * Cache date formatted string
     */
    fun setLastLogDate(date: String){
        sharedPreferences.edit{
            putString("date",date)
        }
    }

    /**
     * get date formatted string
     */
    fun getLastLogDate(): String{
        return sharedPreferences.getString("date","")!!
    }

}