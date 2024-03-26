package com.example.retrofit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.R
import com.example.retrofit.data.model.User
import com.example.retrofit.ui.adapter.UserAdapter
import com.example.retrofit.ui.viewmodel.UserViewModel
import com.example.retrofit.utils.Status
import kotlinx.android.synthetic.main.fragment_user_home.*

class UserHomeFragment : Fragment() {
    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(
            this,
            UserViewModel.UserViewModelFactory(requireActivity().application)
        )[UserViewModel::class.java]
    }

    private val test by viewModels<UserViewModel>()
    private val adapter: UserAdapter by lazy {
        UserAdapter(requireContext(), onItemClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        btn_open_add_activity.setOnClickListener{
            controller.navigate(R.id.action_userHomeFragment_to_addUserFragment)
        }
        rcv_User.layoutManager = LinearLayoutManager(requireContext())
        rcv_User.adapter = adapter
        swipe_layout.setOnRefreshListener {
            refreshData()
        }
        refreshData()
    }
    private val onItemClick: (User) -> Unit = {
    }

    private fun refreshData() {
        userViewModel.getUserFromAPi().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        swipe_layout.isRefreshing = false
                        resource?.data.let { users ->
                            if (users != null) {
                                adapter.setUser(users)
                            }
                        }
                    }
                    Status.ERROR -> {
                        swipe_layout.isRefreshing = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        swipe_layout.isRefreshing = true
                    }
                }
            }
        }
    }




}