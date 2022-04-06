package com.mistershorr.loginandregistration

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.mistershorr.loginandregistration.databinding.ActivityRegistrationBinding
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // access any values that were sent to us from the intent that launched this activity
        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME)
        val password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD)
        Toast.makeText(this, "user:$username pwd $password", Toast.LENGTH_SHORT).show()


        binding.buttonRegistrationRegister.setOnClickListener {
            // TODO: verify that the information they entered is valid

            // in a real app, we'd talk to a database here and save the new user

            // return to the Login Screen and send back the user & pass to prefill
            if(!RegistrationUtil.validateName(
                    binding.editTextRegistrationName.text.toString())) {
                Toast.makeText(this, "Invalid name", Toast.LENGTH_SHORT).show()
            }

            else {
                var returnToLoginIntent = Intent().apply {
                    putExtra(
                        LoginActivity.EXTRA_USERNAME,
                        binding.editTextRegistrationUsername.text.toString()
                    )
                    putExtra(
                        LoginActivity.EXTRA_PASSWORD,
                        binding.editTextRegistrationPassword.text.toString()
                    )
                }
                setResult(RESULT_OK, returnToLoginIntent)
                finish()
            }// closes the activity
        }
    }

    private fun registerUser() {
        val user = BackendlessUser()
        user.setProperty("email", "jamesbond@mi6.co.uk")
        user.password = "iAmWatchingU"

        Backendless.UserService.register(user, object: AsyncCallback<BackendlessUser?>) {

            override fun handleResponse(registeredUser: BackendlessUser?){
                //s
            }

            override fun handleFault(fault: BackendlessFault) {}

        }

    }
}










