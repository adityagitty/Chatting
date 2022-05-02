package com.example.chatting
import android.content.Context
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

 class MessageAdapter(val context:Context,val messageList:ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     val ITEM_RECEIVE = 1
    val ITEM_SENT = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1){
            val view:View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return receiveViewHolder(view)
        }else{
            val view:View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return sentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if (holder.javaClass == sentViewHolder::class.java){

     val ViewHolder = holder as sentViewHolder
      holder.sentMessage.text = currentMessage.message
    }else{
      val holder = holder as receiveViewHolder
         holder.receiveMessage.text = currentMessage.message

     }


        }
    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderID)){
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }


    }

    class sentViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
     val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    class receiveViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
      val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)
    }

     override fun getItemCount(): Int {
         return messageList.size
     }

 }