package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.betrybe.trybnb.R
import com.betrybe.trybnb.ui.viewmodels.ProfileViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val loginInput: TextInputEditText = view.findViewById(R.id.login_input)
        val loginLayout: TextInputLayout = view.findViewById(R.id.login_input_profile)
        val passwordInput: TextInputEditText = view.findViewById(R.id.password_input)
        val passwordLayout: TextInputLayout = view.findViewById(R.id.password_input_profile)
        val loginButton: Button = view.findViewById(R.id.login_button_profile)

        loginButton.setOnClickListener {
            val login = loginInput.text.toString()
            val password = passwordInput.text.toString()
            profileViewModel.validateFields(login, password)
        }

        profileViewModel.loginError.observe(viewLifecycleOwner) { errorMessage ->
            loginLayout.error = errorMessage
        }

        profileViewModel.passwordError.observe(viewLifecycleOwner) { errorMessage ->
            passwordLayout.error = errorMessage
        }

        return view
    }
}