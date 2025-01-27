package com.example.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherforecast.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = FragmentMainScreenBinding.inflate(layoutInflater,container,false)


        return binding.root
    }


}