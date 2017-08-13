package by.test.belarussian.belarussiantests.bizlogic

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import by.test.belarussian.belarussiantests.R
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Questions
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

class AuxMethods {/*
    private val SHARED_PREFERENCES_KEY: String = "player"

    fun saveBestPlayer(context: Context) {
        var listRange = 5
        if (Quiz.bestPlayers.size < 5) {
            listRange = Quiz.bestPlayers.size
        }
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var editor: SharedPreferences.Editor = sharedPref.edit()
        for (i in 0 until listRange) {
            if (Quiz.bestPlayers.get(i) != null) {
                editor.putString(SHARED_PREFERENCES_KEY + i, Quiz.bestPlayers.get(i).toString())
            }
        }

    }

    fun readBestPalyer(context: Context) {
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        //var savedData = mutableMapOf<String, String>(sharedPref.all)
    }

    fun parseJson(questions: Questions, context: Context) {
        var mapper = ObjectMapper()
        var inputStream = context.resources.openRawResource(R.raw.questions)
        var baos = ByteArrayOutputStream()
        var buffer = byteArrayOf()
        var length: Int

        while ((length = inputStream.read(buffer)) != -1) {

        }


    }

*/
}