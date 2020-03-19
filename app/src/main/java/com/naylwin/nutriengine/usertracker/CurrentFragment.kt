package com.naylwin.nutriengine.usertracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.naylwin.eatrition.database.FoodDatabase
import com.naylwin.nutriengine.R
import com.naylwin.nutriengine.databinding.FragmentCurrentBinding
import java.text.DecimalFormat

/**
 * A simple [Fragment] subclass.
 */
class CurrentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCurrentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_current, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).userActivityDao
        val arguments = CurrentFragmentArgs.fromBundle(arguments!!)
        var name = arguments.userInfo[0]
        var date = arguments.userInfo[1]
        val viewModelFactory = CurrentViewModelFactory(dataSource, name, date)
        val currentViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentViewModel::class.java)
        binding.currentViewModel = currentViewModel
        binding.setLifecycleOwner(this)
        updatebindingView(binding, name, date)
        binding.gobackButton.setOnClickListener {
            this.findNavController().navigate(CurrentFragmentDirections.actionCurrentFragmentToUserHomeFragment(arguments.userInfo))
        }

        return binding.root
    }

    private fun updatebindingView(binding: FragmentCurrentBinding, name: String , date: String){
       // binding.invalidateAll()
        val decimalFormat = DecimalFormat("0.00")
        binding.updateButton.setOnClickListener {
                val userActivity = binding.currentViewModel?.updateActivity(name, date)
                userActivity?.apply{
                    binding.nameText.text = user_name
                    binding.dateText.text = date
                    binding.calAmountText.text = calories.toString()
                    binding.sugarAmtText.text = decimalFormat.format(sugar) + "g"
                    binding.sodiumAmtText.text = sodium.toString() + "mg"
                    binding.vitcAmtText.text = decimalFormat.format(vit_c) + "mg"
                    binding.vitaAmtText.text = vit_a.toString() + "rae"
            }
        }
    }

}
