package com.kajtekrut.persons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.persons.databinding.ActivitySecondBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val sharedPreferencesManager = SharedPreferencesManager(this)
    private lateinit var adapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonAdapter(sharedPreferencesManager.getPersons()) { person ->
            sharedPreferencesManager.removePerson(person)
            adapter.updateList(sharedPreferencesManager.getPersons())
        }
        binding.recyclerView.adapter = adapter
    }
}