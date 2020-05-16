package ww.misNotas.notas

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ww.misNotas.notas.adapter.NotesAdapter
import ww.misNotas.notas.db.CrearNotas

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NotesAdapter
    lateinit var tasks: MutableList<CrearNotas>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        tasks = ArrayList()


        fabButton.setOnClickListener {
            val intent = Intent(this, NewNotesActivity::class.java)
            startActivity(intent)
        }


        getNotes()
    }


    fun getNotes() {
        doAsync {
            tasks = Notes.databasenotes.notasDAO().getAllNotes()
            uiThread {
                setUpRecyclerView(tasks)
            }
        }
    }


    fun setUpRecyclerView(tasks: List<CrearNotas>) {
        adapter = NotesAdapter(tasks)
        recyclerView = findViewById(R.id.rvNotas)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}
