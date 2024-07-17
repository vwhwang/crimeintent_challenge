package com.example.crimeintent

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.crimeintent.databinding.ListItemCrimeBinding
import com.example.crimeintent.databinding.ListItemCrimePoliceBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeRequirePoliceHolder(
    private val binding: ListItemCrimePoliceBinding
) : ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        val simpleDateFormat = SimpleDateFormat("MMMM dd, yyyy")
        val dateTime = simpleDateFormat.format(crime.date)
        binding.crimeDate.text = dateTime

        binding.contactButton.text = "contact police"
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == 0 ) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
            CrimeRequirePoliceHolder(binding)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            CrimeHolder(binding)
        }
    }

    override fun getItemCount() = crimes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is CrimeHolder -> holder.bind(crime)
            is CrimeRequirePoliceHolder -> holder.bind(crime)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) {
            0
        } else 1
    }
}