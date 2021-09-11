package com.tob1.example.test.drawerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setEventMenuToolbar(toolbar)


        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        navController = navHostFragment.navController
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)

        setupNavigationMenu(navController)

    }

    // set lai su kien ->>>
    private fun setEventMenuToolbar(toolbar: Toolbar){
        toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.action_menu){
                drawerLayout.openDrawer(GravityCompat.START)
                return@setOnMenuItemClickListener true
            }
            return@setOnMenuItemClickListener onOptionsItemSelected(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }
    // Co chung nang goi lai id cua fragment dang chay
    private fun checkId(): Int{
        return navHostFragment.childFragmentManager.fragments[0].id
    }

    private fun setupNavigationMenu(navController: NavController){
        val slideNavView = findViewById<NavigationView>(R.id.nav_view)
        slideNavView?.setupWithNavController(navController)

        setEventDrawer(slideNavView)
    }

    // https://developer.android.com/guide/navigation/navigation-global-action
    // Vi minh ko the check tat ca truong hop nen lam local action
    private fun setEventDrawer(slideNavView : NavigationView){
        slideNavView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.action_a -> {
                    Log.d("check","GO A")
                    if(checkId() != R.id.fragmentA) navController.navigate(R.id.move_a)
                    drawerLayout.close()
                    true
                }
                R.id.action_b -> {
                    Log.d("check","GO B")
                    if(checkId() != R.id.fragmentC) navController.navigate(R.id.move_b)
                    drawerLayout.close()
                    true
                }
                R.id.action_c -> {
                    Log.d("check","GO C")
                    if(checkId() != R.id.fragmentC) navController.navigate(R.id.move_c)
                    drawerLayout.close()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}