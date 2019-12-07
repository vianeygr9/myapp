package com.utng.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // se declara la base de datos
    var refDiario: DatabaseReference = FirebaseDatabase.getInstance().getReference("Clase")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // delcarar objetos para recibir elementos de la vista


        var btnReg: Button? = null
        // referencia a base de datos

        btnRegistrar.setOnClickListener { registrarClase() }


    }

        private fun registrarClase(){

            var carrera:String = spiCarreras.selectedItem.toString()
            var materia:String = spiMaterias.selectedItem.toString()
            var tema:String = etxTema.text.toString()
            // Title is required
            if (!TextUtils.isEmpty(tema)) {
                var id:String = ""
                id = refDiario.push().key.toString()

                val leccion =  ClaseK(claseId = id, carrera = carrera, materia = materia, tema = tema)
                // se agrega un hijo dentro de la rama de lecciones
                if (id != null) {
                    refDiario.child("Lecciones").child(id).setValue(leccion)
                    Toast.makeText(this, "Clase registrada", Toast.LENGTH_SHORT).show()
                    println()
                }
            }else{
                Toast.makeText(this, "Ingresa tema", Toast.LENGTH_SHORT).show()
            }
        }

    }