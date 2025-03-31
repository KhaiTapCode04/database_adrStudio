package com.example.contactsapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R

class MainActivity : AppCompatActivity() {
    private val viewModel: ContactViewModel by viewModels()
    private lateinit var adapter: ContactAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        textError = findViewById(R.id.textError)

        adapter = ContactAdapter(emptyList())
        recyclerView.adapter = adapter

        // Observe LiveData từ ViewModel
        viewModel.contacts.observe(this) { contacts ->
            adapter.updateContacts(contacts)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { error ->
            if (error.isNotEmpty()) {
                textError.text = error
                textError.visibility = View.VISIBLE
            } else {
                textError.visibility = View.GONE
            }
        }

        // Tải dữ liệu danh bạ khi khởi động
        viewModel.loadContacts("user1")
    }
}