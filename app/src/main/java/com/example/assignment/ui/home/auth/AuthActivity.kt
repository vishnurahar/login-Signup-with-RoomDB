package com.example.assignment.ui.home.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.assignment.R
import com.example.assignment.databinding.ActivityAuthBinding
import com.example.assignment.ui.home.HomeActivity
import com.example.assignment.util.PreferenceManager

class AuthActivity : AppCompatActivity() {

    private var authViewModel: AuthViewModel? = null
    lateinit var binding:ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProviders.of(this, AuthViewModel.Factory(applicationContext)).get(
            AuthViewModel::class.java
        )
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_auth
        )
        binding.viewmodel = authViewModel

        observe()
    }

    private fun observe() {
        authViewModel?.signUpPage?.observe(this, Observer {
            if (it) {
                binding.editTextFullName.visibility = View.VISIBLE
                binding.mainButton.text = "SignUp"
                binding.subBtn.text = "Login"
            } else {
                binding.editTextFullName.visibility = View.GONE
                binding.mainButton.text = "Login"
                binding.subBtn.text = "SignUp"
            }
        })

        authViewModel?.ctaClicked?.observe(this, Observer {
            if (authViewModel!!.signUpPage.value == true) {
                if (validateSignUpFields()) {
                    val email = binding.editTextLoginEmail.text.toString()
                    val password = binding.editTextLoginPassword.text.toString()
                    val name = binding.editTextFullName.text.toString()
                    authViewModel!!.createUser(email = email, password = password, name = name)

                    PreferenceManager.isLoggedIn = true
                    PreferenceManager.email = email
                    PreferenceManager.name = name

                    Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(AuthActivity@ this, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (validateLoginFields()) {
                    val email = binding.editTextLoginEmail.text.toString()
                    val password = binding.editTextLoginPassword.text.toString()
                    if (authViewModel!!.checkValidLogin(email = email, password = password)) {


                        val user = authViewModel!!.getLoggedInUser(email)
                        val email = user.email
                        val name = user.name

                        PreferenceManager.isLoggedIn = true
                        PreferenceManager.email = email
                        PreferenceManager.name = name

                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                        val intent = Intent(AuthActivity@this,HomeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun validateLoginFields(): Boolean {
        val email = binding.editTextLoginEmail.text.toString()
        val password = binding.editTextLoginPassword.text.toString()
        if(email.length > 56) {
            Toast.makeText(this, "Max Length allowed is 56", Toast.LENGTH_SHORT).show()
            return false
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateSignUpFields(): Boolean {
        val email = binding.editTextLoginEmail.text.toString()
        val password = binding.editTextLoginPassword.text.toString()
        val name = binding.editTextFullName.text.toString()
        if(email.length > 56) {
            Toast.makeText(this, "Max Length allowed is 56", Toast.LENGTH_SHORT).show()
            return false
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6 || password.length > 27){
            Toast.makeText(this, "Password Length, Min: 6 and Max: 27", Toast.LENGTH_SHORT).show()
            return false
        }
        if(name.equals("")){
            Toast.makeText(this, "Name Required", Toast.LENGTH_SHORT).show()
            return false
        }
        if(name.length>40){
            Toast.makeText(this, "Max Length allowed is 40 for Name", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}