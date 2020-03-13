package com.naylwin.nutriengine.usertracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.naylwin.nutriengine.R
import com.naylwin.nutriengine.databinding.FragmentUserHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class UserHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentUserHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_home, container, false)
        val arguments = UserHomeFragmentArgs.fromBundle(arguments!!)

        binding.currentButton.setOnClickListener{
            this.findNavController().navigate(UserHomeFragmentDirections.actionUserHomeFragmentToCurrentFragment(arguments.userInfo))
        }
        binding.foodButton.setOnClickListener{
            this.findNavController().navigate(UserHomeFragmentDirections.actionUserHomeFragmentToFoodFragment(arguments.userInfo))
        }

        binding.historyButton.setOnClickListener{
            this.findNavController().navigate(UserHomeFragmentDirections.actionUserHomeFragmentToFoodFragment(arguments.userInfo))
        }

        binding.goBackButton.setOnClickListener {
            this.findNavController().navigate(UserHomeFragmentDirections.actionUserHomeFragmentToHomeFragment())
        }
       return binding.root
    }


}
