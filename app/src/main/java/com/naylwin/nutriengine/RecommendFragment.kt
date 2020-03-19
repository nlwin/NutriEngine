package com.naylwin.nutriengine


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.naylwin.nutriengine.databinding.FragmentRecommendBinding
import okhttp3.*
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
private const val PERMISSION_REQUEST = 10
class RecommendFragment : Fragment() {
    lateinit var locationManager: LocationManager
    private var hasGps = false
    private var locationGps: Location? = null

    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecommendBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_recommend, container, false)

        binding.recyclerViewMain.setBackgroundColor(Color.GRAY)
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(context!!)
        //recyclerView_main.adapter = MainAdapter()
        getLocation()


        //IF STATEMENTS BASED ON USER PERONALIZATION... set finQuery
        var finQuery:String
        finQuery = "Sugar"

        //IF STATEMENTS BASED ON USER PERONALIZATION... set finQuery


        println("MY LSLSLSLLLSLSLLS" + finQuery)
        fetchYelp(locationGps!!.latitude.toDouble(), locationGps!!.longitude.toDouble(), finQuery, binding)

        return binding.root
    }


    fun fetchYelp(lat : Double, long: Double, query : String, binding: FragmentRecommendBinding){
        println("Starting API Attempt")
        val limit = "limit=3" //add this to the end of the final query
        val url = "https://api.yelp.com/v3/businesses/search?"

        //AT LONG and QUERY SET UP
        val fLat = "latitude="+lat.toString()
        val fLong = "longitude="+long.toString()
        val fQuery = "term="+query

        //COMBINE LAT LONG AND QUERY TO FINAL URL
        val finalurl = url + fLat + "&"+ fLong + "&" + fQuery + "&" + limit

        println(finalurl)
        //AUTHORIZATION HEADER CREATION
        val Api_Key = "XXXX"
        val mix = "Bearer " + Api_Key


        val request = Request.Builder().header("Authorization",mix).url(finalurl).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

                //Create Json reader and read into the yelpfeed class
                val gson = GsonBuilder().create()
                val yelpFeed = gson.fromJson(body,YelpFeed::class.java)

                //Create the UI from info from json
                activity?.runOnUiThread {
                    binding.recyclerViewMain.adapter = MainAdapter(yelpFeed)}
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }


    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (hasGps){
            if (hasGps) {
                Log.d("CodeAndroidLocation", "hasGps")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, object :
                    LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        if (location != null) {
                            locationGps = location
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                    }

                    override fun onProviderEnabled(provider: String?) {

                    }

                    override fun onProviderDisabled(provider: String?) {

                    }

                })

                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null) {
                    locationGps = localGpsLocation
                }
            }

        } else {
            //startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }
}



class YelpFeed(val businesses: List<Business>)

class Business(val id: String, val name: String, val image_url: String)