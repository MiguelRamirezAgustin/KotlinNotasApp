package ww.misNotas.notas

import android.app.Application
import android.content.Context
import androidx.room.Room
import ww.misNotas.notas.db.CrearNotasDB

class Notes:Application() {

    companion object{
        lateinit var databasenotes:CrearNotasDB
    }

    override fun onCreate() {
        super.onCreate()
        databasenotes = Room.databaseBuilder(this,CrearNotasDB::class.java, "notas.db").build()
    }
}