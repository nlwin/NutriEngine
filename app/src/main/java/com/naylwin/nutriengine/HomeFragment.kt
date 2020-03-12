package com.naylwin.nutriengine


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.naylwin.eatrition.database.FoodDatabase
import com.naylwin.nutriengine.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).foodDao
        val viewModelFactory = HomeViewModelFactory(dataSource, this.context!!)
        val homeViewModel = ViewModelProviders.of(this, viewModelFactory)
                            .get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        binding.setLifecycleOwner(this)
        binding.loginButton.setOnClickListener{
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
        binding.signupButton.setOnClickListener{
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegisterFragment())
        }
        return binding.root
    }
}
