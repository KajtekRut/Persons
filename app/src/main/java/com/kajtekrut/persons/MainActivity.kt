package com.kajtekrut.persons

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.persons.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sharedPreferencesManager = SharedPreferencesManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.zapiszButton.setOnClickListener {
            if (validateInputs()) {
                val person = Person(
                    binding.imieEditText.text.toString(),
                    binding.nazwiskoEditText.text.toString(),
                    binding.wiekEditText.text.toString().toInt(),
                    binding.wzrostEditText.text.toString().toDouble(),
                    binding.wagaEditText.text.toString().toDouble()
                )

                sharedPreferencesManager.savePerson(person)
                Toast.makeText(this, "Osoba zapisana!", Toast.LENGTH_SHORT).show()

                clearInputs()
            }
        }

        binding.ekran2Button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    private fun validateInputs(): Boolean {
        val imie = binding.imieEditText.text.toString().trim()
        val nazwisko = binding.nazwiskoEditText.text.toString().trim()
        val wiek = binding.wiekEditText.text.toString().toIntOrNull()
        val wzrost = binding.wzrostEditText.text.toString().toDoubleOrNull()
        val waga = binding.wagaEditText.text.toString().toDoubleOrNull()

        when {
            imie.isEmpty() -> {
                showError("Imię jest wymagane")
                return false
            }
            nazwisko.isEmpty() -> {
                showError("Nazwisko jest wymagane")
                return false
            }
            wiek == null || wiek <= 0 -> {
                showError("Podaj prawidłowy wiek")
                return false
            }
            wzrost == null || wzrost < 50 || wzrost > 250 -> {
                showError("Podaj wzrost w zakresie 50-250 cm")
                return false
            }
            waga == null || waga < 3 || waga > 200 -> {
                showError("Podaj wagę w zakresie 3-200 kg")
                return false
            }
        }
        return true
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun clearInputs() {
        binding.imieEditText.text.clear()
        binding.nazwiskoEditText.text.clear()
        binding.wiekEditText.text.clear()
        binding.wzrostEditText.text.clear()
        binding.wagaEditText.text.clear()
    }
}