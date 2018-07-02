package com.example.andreavillacis.speechtotext


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResultado.findViewById(R.id.txtResultado) as TextView

    }

    fun getSpeechInput (view:View){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent,10)
        }
        else{
            Toast.makeText(this,"\n" +
                    "Su dispositivo no es compatible con la entrada de voz",Toast.LENGTH_SHORT ).show()
        }
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent){
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            10 -> if (resultCode == Activity.RESULT_OK && data !=null){
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                txtResultado.setText(result.get(0))

            }
        }
    }
}
