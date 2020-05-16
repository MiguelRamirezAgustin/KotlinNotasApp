package ww.misNotas.notas

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_notes.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import ww.misNotas.notas.db.CrearNotas
import java.text.SimpleDateFormat
import java.util.*

class NewNotesActivity : AppCompatActivity() {

    private var editTextTitle:EditText? = null
    private var editTextContent:EditText? = null
    private var imgVoice:ImageView? = null
    private var imgPhoto:ImageView? = null
    private var imgClean:ImageView? = null
    private val RECOGNIZE_SPEECH_ACTIVITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notes)
        supportActionBar!!.hide()

        editTextTitle = findViewById(R.id.titleNote)
        editTextContent = findViewById(R.id.textContentNote)
        imgVoice = findViewById(R.id.imgVoice)
        imgPhoto = findViewById(R.id.imgPhoto)
        imgClean = findViewById(R.id.imgClean)

        imgBack.setOnClickListener {
            showActivityMain()
        }

        imgClean!!.setOnClickListener {
            if (editTextContent!!.text.isEmpty()){
                toasMessage("La nota no tiene contenido para borrar..!!")
            }else{
                editTextContent!!.setText("")
            }
        }

        buttonNote.setOnClickListener {
            if (editTextContent!!.text.isEmpty()){
                toasMessage("Es requerido agregar el contenido de la nota")
            }else{
                val formater = SimpleDateFormat("yyyy-MM-dd H:mm")
                var formateDate = formater.format(Date())
               doAsync {
                   var notesCreate = CrearNotas(0,editTextTitle!!.text.toString(),
                       editTextContent!!.text.toString(),formateDate,"")
                   Notes.databasenotes.notasDAO().insertNote(notesCreate)
                   onComplete {
                       showActivityMain()
                   }
               }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RECOGNIZE_SPEECH_ACTIVITY ->
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val speech = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    val strSpeech2Text = speech[0]
                    if (editTextContent!!.text.isEmpty()){
                        editTextContent?.setText(strSpeech2Text)
                    }else{
                        var contenidoActual = editTextContent?.text
                        var contenidoGeneral = contenidoActual.toString() +"\n"+ strSpeech2Text.toString()
                        editTextContent?.setText(contenidoGeneral)
                    }
                }
            else -> {
            }
        }
    }

    fun hablar(v: View) {
        val intentActionRecognizeSpeech = Intent(
            RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX")
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                RECOGNIZE_SPEECH_ACTIVITY)
        } catch (a: ActivityNotFoundException) {
            toasMessage("Tú dispositivo no soporta el reconocimiento por voz")
        }
    }



    private fun showActivityMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun Context.toasMessage(message:String){
        val toas = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toas.setGravity(Gravity.CENTER,0,0)
        toas.show()
    }

}
