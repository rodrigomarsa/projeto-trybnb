package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.models.LoginRequest
import com.betrybe.trybnb.databinding.FragmentProfileBinding
import com.betrybe.trybnb.ui.viewmodels.ProfileViewModel
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButtonProfile.setOnClickListener {
            val login = binding.loginInput.text.toString()
            val password = binding.passwordInput.text.toString()
            validateLogin(login)
            validatePassword(password)
            if (validateLogin(login) && validatePassword(password)) {
                val request = LoginRequest(login, password)
                profileViewModel.getToken(request)
            }
            val error = profileViewModel.isLoginError.value
            if (error) {
                Snackbar.make(
                    binding.profileScrollView,
                    "Login ou senha inválidos",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    binding.profileScrollView,
                    "Login feito com sucesso!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validateLogin(login: String): Boolean {
        return if (login.isEmpty()) {
            binding.loginInputProfile.error = "O campo Login é obrigatório"
            false
        } else {
            binding.loginInputProfile.error = null
            true
        }
    }

    private fun validatePassword(password: String): Boolean {
        return if (password.isEmpty()) {
            binding.passwordInputProfile.error = "O campo Password é obrigatório"
            false
        } else {
            binding.passwordInputProfile.error = null
            true
        }
    }
}
