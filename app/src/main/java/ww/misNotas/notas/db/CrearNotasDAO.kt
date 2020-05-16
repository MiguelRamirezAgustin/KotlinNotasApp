package ww.misNotas.notas.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CrearNotasDAO {
    @Insert
    fun insertNote(notes:CrearNotas):Long

    @Query("SELECT * FROM creatNota")
    fun getAllNotes():MutableList<CrearNotas>
}