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
import com.naylwin.nutriengine.databinding.FragmentRegisterBinding

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_register, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).userDao
        val viewModelFactory = RegisterViewModelFactory(dataSource)
        val registerViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(RegisterViewModel::class.java)
        binding.registerViewModel = registerViewModel
        binding.setLifecycleOwner(this)

        binding.registerButton.setOnClickListener{
            var name = binding.nameText.toString()
            var age = binding.ageText.toString()
            var height = binding.heightText.toString()
            var weight = binding.weightText.toString()
            while(name == "" || age == "" || height == "" || weight == ""){
                Toast.makeText(this.context, "Please Enter Full Information", Toast.LENGTH_SHORT).show()
                name = binding.nameText.toString()
                age = binding.ageText.toString()
                height = binding.heightText.toString()
                weight = binding.weightText.toString()
            }
            registerViewModel.registerUser(name, age.toInt(), height.toInt(), weight.toInt())
            this.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
        }

        binding.goBackButton.setOnClickListener {
            this.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
        }

        return binding.root
    }


}
