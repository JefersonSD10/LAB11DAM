package dev.jj.lab11dam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ListadoActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var equipoAdapter: EquipoAdapter
    private var equiposList = mutableListOf<Equipo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listado_activity)

        recyclerView = findViewById(R.id.recyclerViewTeams)
        recyclerView.layoutManager = LinearLayoutManager(this)

        equipoAdapter = EquipoAdapter(equiposList)
        recyclerView.adapter = equipoAdapter

        db = FirebaseFirestore.getInstance()

        // Obtener los equipos en tiempo real
        db.collection("equipos")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Si ocurre un error, lo mostramos
                    exception.printStackTrace()
                    return@addSnapshotListener
                }

                // Limpiar la lista antes de añadir los nuevos datos
                equiposList.clear()

                // Verificar si tenemos un snapshot válido
                snapshot?.let { result ->
                    // Recorrer todos los documentos en el snapshot y añadirlos a la lista
                    for (document in result.documents) {
                        val equipo = document.toObject(Equipo::class.java)
                        equipo?.let {
                            equiposList.add(it)
                        }
                    }

                    // Notificar al adaptador que los datos han cambiado
                    equipoAdapter.notifyDataSetChanged()
                }
            }
    }
}

