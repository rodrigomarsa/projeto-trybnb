package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.BookingDates
import com.betrybe.trybnb.databinding.FragmentCreateReservationBinding
import com.betrybe.trybnb.ui.viewmodels.CreateReservationViewModel
import com.google.android.material.snackbar.Snackbar

class CreateReservationFragment : Fragment() {
    private lateinit var binding: FragmentCreateReservationBinding
    private val viewModel: CreateReservationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_create_reservation, container, false)
        binding = FragmentCreateReservationBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createReservationButton.setOnClickListener {
            validateFields()

            if (validateFields()) {
                viewModel.createBooking(
                    Booking(
                        binding.firstNameInput.text.toString(),
                        binding.lastNameInput.text.toString(),
                        binding.totalPriceInput.text.toString().toInt(),
                        binding.depositpaidCreateReservation.isChecked,
                        BookingDates(
                            binding.checkinInput.text.toString(),
                            binding.checkoutInput.text.toString()
                        ),
                        binding.additionalNeedsInput.text.toString(),
                    )
                )
            }
            val error = viewModel.isErrorOccurred.value
            if (error) {
                Snackbar.make(
                    binding.createReservationScrollView,
                    "Erro ao criar reserva",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    binding.createReservationScrollView,
                    "Reserva feita com sucesso!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (binding.firstNameInput.text.isNullOrEmpty()) {
            binding.firstNameCreateReservation.error = "O campo Nome é obrigatório"
            isValid = false
        } else {
            binding.firstNameCreateReservation.error = null
        }

        if (binding.lastNameInput.text.isNullOrEmpty()) {
            binding.lastNameCreateReservation.error = "O campo Sobrenome é obrigatório"
            isValid = false
        } else {
            binding.lastNameCreateReservation.error = null
        }

        if (binding.checkinInput.text.isNullOrEmpty()) {
            binding.checkinCreateReservation.error = "O campo Checkin é obrigatório"
            isValid = false
        } else {
            binding.checkinCreateReservation.error = null
        }

        if (binding.checkoutInput.text.isNullOrEmpty()) {
            binding.checkoutCreateReservation.error = "O campo Checkout é obrigatório"
            isValid = false
        } else {
            binding.checkoutCreateReservation.error = null
        }

        if (binding.additionalNeedsInput.text.isNullOrEmpty()) {
            binding.additionalNeedsCreateReservation.error =
                "O campo Necessidades Adicionais é obrigatório"
            isValid = false
        } else {
            binding.additionalNeedsCreateReservation.error = null
        }

        if (binding.totalPriceInput.text.isNullOrEmpty()) {
            binding.totalPriceCreateReservation.error = "O campo Preço Total é obrigatório"
            isValid = false
        } else {
            binding.totalPriceCreateReservation.error = null
        }

        return isValid
    }

}