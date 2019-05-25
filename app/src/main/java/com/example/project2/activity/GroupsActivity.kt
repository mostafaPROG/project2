package com.example.project2.activity

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project2.R
import com.jaredrummler.materialspinner.MaterialSpinner
import org.json.JSONObject
import java.util.logging.Logger


class GroupsActivity : AppCompatActivity() {

    var ostanList: ArrayList<String> = ArrayList()
    var cityList: ArrayList<String> = ArrayList()
    lateinit var spinnerOstan: MaterialSpinner
    lateinit var spinnerCity: MaterialSpinner
    private lateinit var back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        back = findViewById(R.id.backGroup)
        back.setOnClickListener { onBackPressed() }

        val typface = Typeface.createFromAsset(this.assets, "font/yekan.ttf")


        val jsonfile: String = applicationContext.assets.open("Province.json")
            .bufferedReader().use { it.readText() }

        Logger.getLogger("tag").warning("is .....     $jsonfile")
        spinnerOstan = findViewById(R.id.spinnerOstan)
        spinnerCity = findViewById(R.id.spinnerCity)

        val obj = JSONObject(jsonfile)
        var ostans = obj.getJSONArray("ostans")
        (0 until (ostans.length())).forEach {
            ostanList.add(ostans.getJSONObject(it).getString("name"))
        }
        val ostanAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ostanList)
        ostanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOstan.setAdapter(ostanAdapter)
        var cityObj = ostans.getJSONObject(0).getJSONArray("Cities")
        (0 until (cityObj.length())).forEach {
            cityList.add(cityObj.getJSONObject(it).getString("name"))
        }
        cityList.sort()
        val cityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityList)
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.setAdapter(cityAdapter)

        spinnerOstan.setOnItemSelectedListener { view, position, id, item ->
            cityList.clear()
            spinnerCity.clearComposingText()
            var cityObj = ostans.getJSONObject(position).getJSONArray("Cities")
            (0 until (cityObj.length())).forEach {
                cityList.add(cityObj.getJSONObject(it).getString("name"))
            }
            cityList.sort()
            val cityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityList)
            cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCity.setAdapter(cityAdapter)

        }

    }

}
