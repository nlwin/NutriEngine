package com.naylwin.nutriengine.usertracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.naylwin.eatrition.database.FoodDatabase
import com.naylwin.nutriengine.R
import com.naylwin.nutriengine.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).userActivityDao
        val viewModelFactory = LoginViewModelFactory(dataSource)
        val loginViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)
        binding.loginViewModel = loginViewModel
        binding.setLifecycleOwner(this)


        binding.loginButton.setOnClickListener{
            var name = binding.nameText.text.toString()
            var day = binding.dayText.text.toString()
            var month = binding.monthText.text.toString()
            var year = binding.yearText.text.toString()
            while(name == "" || day == "" || month == "" || year == "") {
                Toast.makeText(this.context, "Please Put Name and Date", Toast.LENGTH_SHORT).show()
                name = binding.nameText.text.toString()
                day = binding.dayText.text.toString()
                month = binding.monthText.text.toString()
                year = binding.yearText.text.toString()
            }
            val date = "${day}-${month}-${year}"
            loginViewModel.logInAction(name, date)
            this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToUserHomeFragment(
                arrayOf(name, date)))
        }

        binding.gobackButton.setOnClickListener{
            this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
        return binding.root
    }

    fun loginUser(){

    }

}
