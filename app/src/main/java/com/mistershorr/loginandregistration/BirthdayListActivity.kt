package com.mistershorr.birthdaytracker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.mistershorr.loginandregistration.databinding.ActivityBirthdayListBinding

class BirthdayListActivity : AppCompatActivity() {

    lateinit var binding : ActivityBirthdayListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBirthdayListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabBirthdayListCreateNew
    }


    private fun loadDataFromBackendless() {

        val queryBuilder = DataQueryBuilder.create()
        Backendless.Data.of(Person::class.java).find(object : AsyncCallback<List<Person?>?> {
            override fun handleResponse(foundPeople: List<Person?>?) {
                val adapter = BirthdayAdapter(((foundPeople ?: listOf<Person>()) as List<Person>))
                //Log.d(TAG, "Total restaurants in the Backendless storage - $count")
                //Backendless.Data.of(Person::class.java).find(callback)
            }

            override fun handleFault(fault: BackendlessFault) {
                //Log.e(TAG, fault.message)
            }

        })
    }
}