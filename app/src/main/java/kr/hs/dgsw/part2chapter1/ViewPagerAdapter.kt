package kr.hs.dgsw.part2chapter1

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity): FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                Log.d("TAG", "createFragment: $position")
                return WebViewFragment(position, "https://comic.naver.com/index").apply {
                    listener = mainActivity
                }
            }
            1->{
                Log.d("TAG", "createFragment: $position")
                return WebViewFragment(position, "https://comic.naver.com/webtoon").apply {
                    listener = mainActivity
                }
            }
            else -> {
                Log.d("TAG", "createFragment: $position")
                return WebViewFragment(position, "https://comic.naver.com/bestChallenge").apply {
                    listener = mainActivity
                }
            }
        }
    }
}