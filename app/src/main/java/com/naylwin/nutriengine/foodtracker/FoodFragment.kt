package com.naylwin.nutriengine.foodtracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.naylwin.eatrition.database.FoodDatabase
import com.naylwin.nutriengine.R
import com.naylwin.nutriengine.databinding.FragmentFoodBinding
import com.naylwin.nutriengine.formatFood

/**
 * A simple [Fragment] subclass.
 */
class FoodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentFoodBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_food, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application)
        val arguments = FoodFragmentArgs.fromBundle(arguments!!)
        val viewModelFactory = FoodViewModelFactory(dataSource.foodDao, dataSource.userActivityDao, this.context!!)
        val foodViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(FoodViewModel::class.java)
        binding.foodViewModel =  foodViewModel
        binding.setLifecycleOwner(this)

        binding.foodNameButton.setOnClickListener {
            var foodName = binding.foodnameText.text.toString()
            foodName = "%${foodName}%"
            val foodList = foodViewModel.getFood(foodName)
            binding.textView.text = formatFood(foodList)
        }
        binding.foodIdButton.setOnClickListener {
            val foodId = binding.foodidText.text.toString().toInt()
            foodViewModel.addFood(arguments.userInfo[0], arguments.userInfo[1], foodId)
        }

        return binding.root

    }


}
