package com.example.contactsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactsapp.R

class ContactAdapter(private var contacts: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.textName)
        val phoneText: TextView = view.findViewById(R.id.textPhone)
        val emailText: TextView = view.findViewById(R.id.textEmail)
        val addressText: TextView = view.findViewById(R.id.textAddress)
        val photoImage: ImageView = view.findViewById(R.id.imagePhoto)
        val favoriteIcon: ImageView = view.findViewById(R.id.imageFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameText.text = contact.name
        holder.phoneText.text = contact.phone
        holder.emailText.text = contact.email
        holder.addressText.text = contact.address

        if (contact.is_favorite == 1) {
            holder.favoriteIcon.visibility = View.VISIBLE
        } else {
            holder.favoriteIcon.visibility = View.GONE
        }

        // Nếu URL hình ảnh là tên file đơn giản, bạn cần thêm base URL
        val imageUrl = "http://localhost/images/${contact.photo}"
        Glide.with(holder.photoImage.context)
            .load(imageUrl)

            .into(holder.photoImage)
    }

    override fun getItemCount() = contacts.size

    fun updateContacts(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }

}