package ww.misNotas.notas.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "creatNota")
data class CrearNotas(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idNota")
    var idNota: Int = 0,
    @ColumnInfo(name = "titulo")
    var titulo: String = "",
    @ColumnInfo(name = "contenido")
    var contenido: String = "",
    @ColumnInfo(name = "fechaCracion")
    var fechaCreacion: String = "",
    @ColumnInfo(name = "fechaActualizacion")
    var fechaActualizacion: String = ""

) {}