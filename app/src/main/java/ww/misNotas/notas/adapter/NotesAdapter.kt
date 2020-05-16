package ww.misNotas.notas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ww.misNotas.notas.R
import ww.misNotas.notas.db.CrearNotas

class NotesAdapter (private val toasks:List<CrearNotas>):RecyclerView.Adapter<NotesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_notas,parent,false))
    }

    override fun getItemCount(): Int {
        return toasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=toasks[position]
        holder.bind(item)
    }


    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val titleNote = view.findViewById<TextView>(R.id.tvTitle)
        private val date = view.findViewById<TextView>(R.id.tvDate)
        private val textContentNotes = view.findViewById<TextView>(R.id.textContentNotes)



        fun bind(toask:CrearNotas){
            if (toask.titulo.isEmpty()){
                titleNote.text = "sin titulo"
                textContentNotes.text = toask.contenido
                date.text = "Fecha: "+toask.fechaCreacion
            }else{
                titleNote.text = toask.titulo
                textContentNotes.text = toask.contenido
                date.text = "Fecha: "+toask.fechaCreacion
            }

        }

    }
}