package com.example.retrofit.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.data.model.User

class UserAdapter(
    private val context : Context,
    private val onclick : (User)-> Unit,
    ) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var users: List<User> = listOf()
    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val tvIdUser = view.findViewById<TextView>(R.id.tv_user_id)
        private val tvNameUser = view.findViewById<TextView>(R.id.tv_user_name)
        private val tvAddressUser = view.findViewById<TextView>(R.id.tv_user_address)
        private val layoutItem = view.findViewById<LinearLayout>(R.id.layout_item)
        fun onBind(user : User){
            tvIdUser.text = user.id.toString()
            tvNameUser.text = user.name
            tvAddressUser.text = user.address
            layoutItem.setOnClickListener{onclick(user)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            holder.onBind(users[position])
    }

    override fun getItemCount(): Int = users.size
    fun setUser(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }
}