package com.example.retrofit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.retrofit.R
import com.example.retrofit.data.model.User
import com.example.retrofit.ui.viewmodel.UserViewModel
import com.example.retrofit.utils.Resource
import com.example.retrofit.utils.Status
import kotlinx.android.synthetic.main.fragment_add_user.*

class AddUserFragment : Fragment() {

    val navController by lazy {
        findNavController()
    }

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(
            this,
            UserViewModel.UserViewModelFactory(requireActivity().application)
        )[UserViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getID = edtIdUser?.text.toString()
        val id : Int = try {
            getID?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            0
        }
        btnSubmit.setOnClickListener {
            val user = User(id, edtUserName.text.toString(), edtUserAddress.text.toString())
            userViewModel.addUserFromApi(user).observe(viewLifecycleOwner) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(requireContext(), it.data?.toString(), Toast.LENGTH_LONG)
                                .show()
                            navController.popBackStack()
                        }
                        Status.ERROR -> {
                            Toast.makeText(requireContext(), "Add data Error", Toast.LENGTH_LONG)
                                .show()
                        }
                        Status.LOADING -> {

                        }
                    }
                }
            }
        }
    }
}


