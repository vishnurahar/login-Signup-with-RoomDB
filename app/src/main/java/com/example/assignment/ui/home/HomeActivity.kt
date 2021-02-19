package com.example.assignment.ui.home

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.R
import com.example.assignment.databinding.ActivityHomeBinding
import com.example.assignment.ui.home.auth.AuthActivity
import com.example.assignment.util.PreferenceManager

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        if(PreferenceManager.isLoggedIn!=null && !PreferenceManager.isLoggedIn){
            val intent = Intent(HomeActivity@this,AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        binding.textViewHomeEmail.text = PreferenceManager.email
        binding.textViewHomeFullName.text = PreferenceManager.name

        viewModel.logoutClicked.observe(this, Observer {
            PreferenceManager.logOut()
            val intent = Intent(HomeActivity@this,AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        })
    }
}