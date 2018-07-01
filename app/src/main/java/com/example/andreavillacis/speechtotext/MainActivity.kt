package com.example.andreavillacis.speechtotext

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var tts:TextToSpeech?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_speak.isEnabled=false
        button_speak.setOnClickListener{speackOut()}
        tts=  TextToSpeech(this,this)
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "El idioma especificado no es compatible")
            }else{
                button_speak.isEnabled = true
            }
        }else
        {
            Log.e("TTS", "La iniciacion falló")
        }
    }


    private fun speackOut() {
       val texto = txt_input.toString()
        tts!!.speak(texto, TextToSpeech.QUEUE_FLUSH, null,"")
    }



    override fun onDestroy() {
        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}
