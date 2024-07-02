package com.example.talkerbanda.messages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.talkerbanda.databinding.ItemChatBinding
import com.bumptech.glide.Glide
import com.example.talkerbanda.R


class ChatAdapter(private var chats: MutableList<Chat>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount(): Int = chats.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateChats(newChats: List<Chat>) {
        chats.clear()
        chats.addAll(newChats)
        notifyDataSetChanged()
    }

    fun addChat(chat: Chat) {
        chats.add(chat)
        notifyItemInserted(chats.size - 1)
    }

    fun removeChat(position: Int) {
        if (position in chats.indices) {
            chats.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    class ChatViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) {
            binding.chat = chat
            binding.executePendingBindings()

            // Пример загрузки и отображения аватара из URL-адреса
            Glide.with(itemView.context)
                .load(chat.avatarUrl) // Загрузка изображения по URL-адресу
                .placeholder(R.drawable.placeholder) // Опциональное отображение заглушки, пока изображение не загружено
                .error(R.drawable.baseline_error_24) // Опциональное отображение ошибки, если изображение не может быть загружено
                .into(binding.chatAvatar) // Установка загруженного изображения в ImageView
        }
    }

}



