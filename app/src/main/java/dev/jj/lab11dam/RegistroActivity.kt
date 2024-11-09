package dev.jj.lab11dam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var editTextTeamName: EditText
    private lateinit var editTextFoundationYear: EditText
    private lateinit var editTextTitlesWon: EditText
    private lateinit var editTextImageUrl: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_activity)

        db = FirebaseFirestore.getInstance()
        editTextTeamName = findViewById(R.id.editTextTeamName)
        editTextFoundationYear = findViewById(R.id.editTextFoundationYear)
        editTextTitlesWon = findViewById(R.id.editTextTitlesWon)
        editTextImageUrl = findViewById(R.id.editTextImageUrl)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val teamName = editTextTeamName.text.toString()
            val foundationYear = editTextFoundationYear.text.toString().toIntOrNull()
            val titlesWon = editTextTitlesWon.text.toString().toIntOrNull()
            val imageUrl = editTextImageUrl.text.toString()

            if (teamName.isNotEmpty() && foundationYear != null && titlesWon != null && imageUrl.isNotEmpty()) {
                val team = hashMapOf(
                    "name" to teamName,
                    "foundationYear" to foundationYear,
                    "titlesWon" to titlesWon,
                    "imageUrl" to imageUrl
                )

                db.collection("equipos")
                    .add(team)
                    .addOnSuccessListener {
                        // Redirigir a ListadoActivity
                        val intent = Intent(this, ListadoActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        // Mostrar error si la operación falla
                        e.printStackTrace()
                    }
            }
        }
    }
}
