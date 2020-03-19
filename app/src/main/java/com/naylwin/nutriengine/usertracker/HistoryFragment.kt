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
import com.naylwin.nutriengine.databinding.FragmentHistoryBinding
import com.naylwin.nutriengine.formatHistory

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHistoryBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_history, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).userActivityDao
        val arguments = HistoryFragmentArgs.fromBundle(arguments!!)
        val viewModelFactory = HistoryViewModelFactory(dataSource, this.context!!)
        val historyViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HistoryViewModel::class.java)
        binding.historyViewModel =  historyViewModel
        val userHistories = historyViewModel.getUserActivities(arguments.userInfo[0])

        binding.textView3.text = formatHistory(userHistories)
        binding.nameText.text = userHistories.get(0)?.user_name
        binding.goBackButton.setOnClickListener {
            this.findNavController().navigate(HistoryFragmentDirections.actionHistoryFragmentToUserHomeFragment(arguments.userInfo))
        }

        binding.setLifecycleOwner(this)
        return binding.root
    }


}
