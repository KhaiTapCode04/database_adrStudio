package com.example.contactsapp

class ContactRepository {
    private val contactApi = RetrofitClient.contactApi

    suspend fun getContacts(userId: String): List<Contact> {
        val response = contactApi.getContacts(userId)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }
}