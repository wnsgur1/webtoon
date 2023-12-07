package kr.hs.dgsw.part2chapter1

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import kr.hs.dgsw.part2chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val tab0 = sharedPreferences.getString("tab0_name", "홈")
        val tab1 = sharedPreferences.getString("tab1_name", "웹툰")
        val tab2 = sharedPreferences.getString("tab2_name", "베스트도전")

        binding.viewPager.adapter = ViewPagerAdapter(this)


        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
//                val textView = TextView(this@MainActivity)
//                textView.text = when(position){
//                    0 -> tab0
//                    1 -> tab1
//                    else -> tab2
//                }
//                textView.gravity = Gravity.CENTER
//                textView.setTextColor(Color.MAGENTA)
//
//                tab.customView = textView
                tab.text = when(position){
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach()



    }





    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem] // TODO fragment를 viewPager에서 가져와야
        if (currentFragment is WebViewFragment){
            if(currentFragment.canGoBack()){
                currentFragment.goBack()
            }else{
                super.onBackPressed()
            }
        }else{
            super.onBackPressed()
        }

    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name

    }
}