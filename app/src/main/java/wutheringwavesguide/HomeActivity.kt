package wutheringwavesguide

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import wutheringwavesguide.binding.HomeViewPagerAdapter
import wutheringwavesguide.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate((layoutInflater))
        enableEdgeToEdge()
        initNavController()
        setContentView(binding.root)
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        this.navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val id = destination.id
            val showNav = id != R.id.characterFragment && id != R.id.characterFragment
//            binding.bottomNav?.isVisible = showNav
        }
    }



//    private fun initNavController() {
//        binding.bottomNav1.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.homeFragment -> replaceFragment(HomeFragment())
//                R.id.characterFragment -> replaceFragment(CharacterFragment())
//                else -> {
//
//                }
//            }
//            true
//        }
//    }
//
//    private fun replaceFragment(fragment : Fragment) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
//        fragmentTransaction.commit()
//    }
}