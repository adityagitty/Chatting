package com.example.chatting

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.User
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context:Context,val userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.userViewHolder>(){
    class userViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.txt_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
     val view:View = LayoutInflater.from(context).inflate(R.layout.user,parent,false)
    return userViewHolder(view)
    }



    override fun getItemCount(): Int {
       return userList.size
    }

     override fun onBindViewHolder(holder: userViewHolder, position: Int) {
     val userCurrent = userList[position]
        holder.textName.text = userCurrent.name
        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("name",userCurrent.name)
            intent.putExtra("uid",userCurrent.uid)
            context.startActivity(intent)
        }
    }
}

