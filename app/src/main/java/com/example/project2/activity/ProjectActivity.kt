package com.example.project2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.project2.R

class ProjectActivity : AppCompatActivity() {

    private lateinit var tools:Button
    private lateinit var register:Button
    private lateinit var map:Button
    private lateinit var toolbar:Toolbar
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)

        tools = findViewById(R.id.toolsP)
        register = findViewById(R.id.groupP)
        map=findViewById(R.id.mapP)

//        toolbar.setNavigationOnClickListener {
//            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
//                drawerLayout.closeDrawer(Gravity.RIGHT);
//            } else {
//                drawerLayout.openDrawer(Gravity.RIGHT);
//            }
//        }

        tools.setOnClickListener { startActivity(Intent(this@ProjectActivity, AppFragment::class.java)) }
//        map.setOnClickListener { startActivity(Intent(this@ProjectActivity,)) }
        register.setOnClickListener { startActivity(Intent(this@ProjectActivity, GroupsActivity::class.java)) }
    }
}
