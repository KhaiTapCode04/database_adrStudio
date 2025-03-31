package com.example.contactsapp

data class Contact(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val address: String,
    val photo: String,
    val user_id: String,
    val is_favorite: Int
)