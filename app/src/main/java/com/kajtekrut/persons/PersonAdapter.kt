package com.kajtekrut.persons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.persons.databinding.ItemPersonBinding

class PersonAdapter(
    private var persons: List<Person>,
    private val onDelete: (Person) -> Unit
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = persons[position]
        holder.binding.imieTextView.text = "Imię: ${person.imie}"
        holder.binding.nazwiskoTextView.text = "Nazwisko: ${person.nazwisko}"
        holder.binding.wiekTextView.text = "Wiek: ${person.wiek}"
        holder.binding.wzrostTextView.text = "Wzrost: ${person.wzrost} cm"
        holder.binding.wagaTextView.text = "Waga: ${person.waga} kg"
        holder.binding.usunButton.setOnClickListener { onDelete(person) }
    }

    override fun getItemCount() = persons.size

    fun updateList(newPersons: List<Person>) {
        persons = newPersons
        notifyDataSetChanged()
    }
}