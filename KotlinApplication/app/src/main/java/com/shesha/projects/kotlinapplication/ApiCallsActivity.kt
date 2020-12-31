package com.shesha.projects.kotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.kotlinapplication.helpers.CountriesAdapter
import com.shesha.projects.kotlinapplication.models.Country
import com.shesha.projects.kotlinapplication.services.CountryService
import com.shesha.projects.kotlinapplication.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCallsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_calls)
        loadCountries()

    }

    private fun loadCountries() {
        var apiResult : RecyclerView = findViewById(R.id.api_recycler_view)
        //initiate the service
        val destinationService  = ServiceBuilder.buildService(CountryService::class.java)
        val requestCall =destinationService.getAffectedCountryList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val countryList  = response.body()!!
                            Log.d("Response", "countrylist size : ${countryList.size}")
                    apiResult.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@ApiCallsActivity,2)
                        adapter = CountriesAdapter(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@ApiCallsActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(this@ApiCallsActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}