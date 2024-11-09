package dev.jj.lab11dam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EquipoAdapter(private val equipos: List<Equipo>) : RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_equipo, parent, false)
        return EquipoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val equipo = equipos[position]
        holder.teamName.text = equipo.name
        holder.foundationYear.text = "Fundado en: ${equipo.foundationYear}"
        holder.titlesWon.text = "Títulos: ${equipo.titlesWon}"

        // Cargar la imagen usando Glide
        Glide.with(holder.itemView.context)
            .load(equipo.imageUrl)
            .into(holder.teamImage)
    }

    override fun getItemCount(): Int {
        return equipos.size
    }

    class EquipoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val teamName: TextView = view.findViewById(R.id.textViewTeamName)
        val foundationYear: TextView = view.findViewById(R.id.textViewFoundationYear)
        val titlesWon: TextView = view.findViewById(R.id.textViewScore)
        val teamImage: ImageView = view.findViewById(R.id.imageViewTeam)
    }
}
