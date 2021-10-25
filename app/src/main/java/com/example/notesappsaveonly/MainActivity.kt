package com.example.notesappsaveonly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var db:DBHlper
    lateinit var rv:RecyclerView
    lateinit var adapter:Adapter
    lateinit var et:EditText
    lateinit var button:Button

    lateinit var items : ArrayList<Notes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHlper(this)

        items = arrayListOf()
        rv = findViewById(R.id.rv)

        et = findViewById(R.id.et)
        button = findViewById(R.id.button)
        button.setOnClickListener { postNote()
            items = db.viewNotes()
            updateRV()}

        items = db.viewNotes()
        updateRV()
    }

    private fun updateRV(){
        adapter = Adapter(items)
        adapter.update(items)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    fun postNote(){
        db.saveData(Notes(0,et.text.toString()))
        et.text.clear()
        Toast.makeText(applicationContext,"note added", Toast.LENGTH_LONG).show()
        updateRV()
    }
}