package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var personList: MutableList<Person> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {

        binding.getUserInfo.setOnClickListener {
            if (binding.searchEditText.isVisible) {
                val person =
                    personList.firstOrNull {
                        it.toString().contains(binding.searchEditText.toString())
                    }
                if (person != null) {
                    binding.userInfoView.text =
                        person.id.toString() + " " + person.firstName + " " + person.lastName
                } else {
                    binding.userInfoView.text = "User not found"
                    binding.saveButton.isEnabled = true
                }
            } else {
                binding.searchEditText.isVisible = true
                binding.inputsLayout.isVisible = false
            }
        }

        binding.saveButton.setOnClickListener {
            binding.searchEditText.isVisible = false
            binding.inputsLayout.isVisible = true
            if (binding.inputsLayout.isVisible) {
                val person = Person(
                    id = personList.size.toLong() + 1L,
                    firstName = binding.firstNameInput.toString(),
                    lastName = binding.lastNameInput.toString(),
                    birthDay = binding.birthdayInput.toString(),
                    email = binding.emailInput.toString(),
                    address = binding.addressInput.toString()
                )
                personList.add(person)
                binding.userInfoView.text =
                    person.id.toString() + " " + person.firstName + " " + person.lastName
            }
        }


    }
}