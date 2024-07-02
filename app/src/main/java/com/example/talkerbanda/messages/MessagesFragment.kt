package com.example.talkerbanda.messages

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.talkerbanda.databinding.FragmentMessagesBinding
import com.example.talkerbanda.viewmodel.MessagesViewModel

class MessagesFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    private val messagesViewModel: MessagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)

        val view = binding.root

        // Setup RecyclerView
        val recyclerView = binding.chatsRecyclerView
        val adapter = ChatAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // Observe chats LiveData and update adapter when data changes
        messagesViewModel.chats.observe(viewLifecycleOwner) { chats ->
            adapter.updateChats(chats)
        }

        binding.viewModel = messagesViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.sendButton.setOnClickListener {
            val messageText = binding.messageEditText.text.toString()
            if (messageText.isNotEmpty()) {
                messagesViewModel.sendMessage(messageText)
                binding.messageEditText.text.clear()
            }
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let { messagesViewModel.searchMessages(it.toString()) }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}










