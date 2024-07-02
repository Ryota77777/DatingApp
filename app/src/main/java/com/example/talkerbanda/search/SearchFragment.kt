package com.example.talkerbanda.search

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.talkerbanda.R
import com.example.talkerbanda.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        val filterCity: CheckBox = binding.filterCity
        val filterInterests: CheckBox = binding.filterInterests
        val filterGender: CheckBox = binding.filterGender
        val filterAge: CheckBox = binding.filterAge

        val spinnerCity: Spinner = binding.spinnerCity
        val spinnerInterests: Spinner = binding.spinnerInterests
        val spinnerGender: Spinner = binding.spinnerGender
        val spinnerAge: Spinner = binding.spinnerAge

        val searchField: EditText = binding.searchField
        val filterLayout: LinearLayout = binding.filterLayout

        // Sample data for spinners
        val cityList = listOf("Москва", "Санкт-Петербург", "Новосибирск")
        val interestsList = listOf("Спорт", "Музыка", "Искусство")
        val genderList = listOf("Мужской", "Женский")
        val ageList = listOf("18-25", "26-35", "36-45")

        // Adapters for spinners
        spinnerCity.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, cityList)
        spinnerInterests.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, interestsList)
        spinnerGender.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, genderList)
        spinnerAge.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, ageList)

        // Toggle spinner visibility based on checkbox state
        filterCity.setOnCheckedChangeListener { _, isChecked ->
            spinnerCity.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
        filterInterests.setOnCheckedChangeListener { _, isChecked ->
            spinnerInterests.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
        filterGender.setOnCheckedChangeListener { _, isChecked ->
            spinnerGender.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
        filterAge.setOnCheckedChangeListener { _, isChecked ->
            spinnerAge.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        // Show filters on search field click
        searchField.setOnClickListener {
            if (filterLayout.visibility == View.GONE) {
                filterLayout.visibility = View.VISIBLE
                // Optional: Add animation
                filterLayout.animate().alpha(1.0f).setDuration(300).start()
            }
        }
    }
}











