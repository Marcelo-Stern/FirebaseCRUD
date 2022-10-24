package com.example.firebasecrud

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNome : EditText = findViewById(R.id.edtNome)
        val edtEndereco : EditText = findViewById(R.id.edtEndereco)
        val edtBairro : EditText = findViewById(R.id.edtBairro)
        val edtCEP : EditText = findViewById(R.id.edtCEP)
        val btnCadastrar : Button = findViewById(R.id.btnCadastrar)
        val btnConsultar : Button = findViewById(R.id.btnConsultar)

        // Access a Cloud Firestore instance from your activity
        val db = Firebase.firestore

        btnCadastrar.setOnClickListener {
            // Create a new user with a first and last name
            val cadastroPessoa = hashMapOf(
                "nome" to edtNome.text.toString(),
                "endereco" to edtEndereco.text.toString(),
                "bairro" to edtBairro.text.toString(),
                "cep" to edtCEP.text.toString()
            )

            // Add a new document with a generated ID
            db.collection("cadastro")
                .add(cadastroPessoa)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    val intent = Intent(this, ReadActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener{ e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

        btnConsultar.setOnClickListener{
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
    }
}