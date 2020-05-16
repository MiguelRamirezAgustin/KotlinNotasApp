package ww.misNotas.notas.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CrearNotas::class),version = 1)

abstract class CrearNotasDB:RoomDatabase() {
    abstract fun notasDAO():CrearNotasDAO
}