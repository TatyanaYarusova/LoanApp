package com.example.yarusovashift.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.R
import com.example.yarusovashift.databinding.FragmentAuthBinding
import com.example.yarusovashift.domain.entity.result.RequestError
import com.example.yarusovashift.presentation.AuthViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import com.example.yarusovashift.presentation.state.AuthState
import com.example.yarusovashift.presentation.state.SuccessState
import com.example.yarusovashift.utils.isValidName
import javax.inject.Inject


class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    private val component by lazy {
        (activity as MainActivity).component
            .fragmentComponent().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
    }

    private fun renderState(state: AuthState) {

        when(state) {
            is AuthState.Login -> renderLoginDialog()
            is AuthState.Registration -> renderRegistrationDialog()
            is AuthState.Loading -> renderLoading()
            is AuthState.Success -> renderSuccess(state.type)
            is AuthState.Error<*> -> renderError(state.type)
        }

    }

    private fun renderLoginDialog() {
        refreshErrorText()
        binding.pb.visibility = GONE
        binding.bottomSheet.visibility = VISIBLE
        binding.login.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_auth_button_active))
        binding.registration.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_auth_button_inactive))
        binding.registration.setOnClickListener { viewModel.openRegistration() }
        binding.authButton.setText(R.string.login_button)

        binding.authButton.setOnClickListener {
            val name = binding.loginTI.text.toString()
            val password = binding.passwordTI.text.toString()
            if (isValidLogin(name, password )) {
                viewModel.login(name, password)
            }
        }
        binding.repeatPasswordTF.visibility = GONE
        binding.errorTextRepeatPassword.visibility = GONE
    }

    private fun renderRegistrationDialog() {
        refreshErrorText()
        binding.pb.visibility = GONE
        binding.bottomSheet.visibility = VISIBLE
        binding.registration.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_auth_button_active))
        binding.login.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_auth_button_inactive))
        binding.login.setOnClickListener { viewModel.openLogin() }
        binding.authButton.setText(R.string.registration_button)
        binding.authButton.setOnClickListener {
            val name = binding.loginTI.text.toString()
            val password = binding.passwordTI.text.toString()
            val repeatPassword = binding.repeatPasswordTI.text.toString()
            if(isValidRegistration(name, password, repeatPassword)){
                viewModel.registration(name, password, repeatPassword)
            }
        }
        binding.repeatPasswordTF.visibility = VISIBLE
        binding.errorTextRepeatPassword.visibility = VISIBLE
    }

    private fun renderLoading() {
        binding.pb.visibility = VISIBLE
        binding.bottomSheet.visibility = GONE
    }

    private fun <T> renderError(type: RequestError<T>) {
        when (type) {
            is RequestError.UserNotFound -> {
                renderLoginDialog()
                binding.errorTextPassword.text = requireContext().resources.getString(R.string.error_login)
            }
            is RequestError.UserAlreadyExists -> {
                renderRegistrationDialog()
                binding.errorTextRepeatPassword.text = requireContext().resources.getString(R.string.error_registration)
            }
            is RequestError.NetworkError -> {
                Toast.makeText(
                    requireContext(),
                    "Произошла ошибка! Проверьте подключение к сети",
                    Toast.LENGTH_LONG
                ).show()
                renderLoginDialog()
            }

            is RequestError.ServerError -> {
                Toast.makeText(
                    requireContext(),
                    "Произошла ошибка сервера! Попробуйте позже",
                    Toast.LENGTH_LONG
                ).show()
                renderLoginDialog()
            }
        }
    }

    private fun renderSuccess(successType: SuccessState ) {
        when(successType){
            is SuccessState.SuccessLogin -> viewModel.openMain()
            is SuccessState.SuccessRegistration -> viewModel.openOnboarding()
        }
    }
    private fun isValidLogin(name: String, password: String): Boolean {
        refreshErrorText()
        var flag = true
        if(name.isBlank()) {
            binding.errorTextLogin.text = requireContext().resources.getString(R.string.error_empty_input)
            flag = false
        }

        if(password.isBlank()) {
            binding.errorTextPassword.text = requireContext().resources.getString(R.string.error_empty_input)
            flag = false
        }

        if(!isValidName(name)){
            binding.errorTextLogin.text = requireContext().resources.getString(R.string.error_login_input)
            flag = false
        }

        return flag
    }

    private fun isValidRegistration(name: String, password: String, repeatPassword: String): Boolean {
        refreshErrorText()
        var flag = isValidLogin(name, password)

        if (password != repeatPassword) {
            binding.errorTextRepeatPassword.text = requireContext().resources.getString(R.string.error_password)
            flag = false
        }

        return flag
    }

    private fun refreshErrorText() {
        binding.errorTextLogin.text = ""
        binding.errorTextPassword.text = ""
        binding.errorTextRepeatPassword.text = ""
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = AuthFragment()
    }
}