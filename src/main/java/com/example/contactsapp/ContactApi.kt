package com.example.contactsapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactApi {
    @GET("contacts_api/get_contacts.php")
    suspend fun getContacts(@Query("user_id") userId: String): Response<List<Contact>>
}