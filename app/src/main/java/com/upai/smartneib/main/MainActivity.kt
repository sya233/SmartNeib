package com.upai.smartneib.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.upai.smartneib.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.common_toolbar.*

class MainActivity : AppCompatActivity(), MainView {

    // Presenter
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化
        init()
        // 点击事件
        respondToClick()
    }

    private fun init() {
        // ActionBar, Toolbar
        setSupportActionBar(toolbar)
        setActionTitle(resources.getString(R.string.notification))
        // Presenter
        mainPresenter = MainPresenter(this, MainModel())
        // ViewPager
        vp_main.adapter = ViewPagerAdapter(supportFragmentManager)
        // 检查是否有版本更新
        mainPresenter.haveNewVersion(this)
    }

    private fun respondToClick() {
        // BottomNavigationView
        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_notification -> {
                    setActionTitle(resources.getString(R.string.notification))
                    vp_main.currentItem = 0
                }
                R.id.menu_service -> {
                    setActionTitle(resources.getString(R.string.service))
                    vp_main.currentItem = 1
                }
                R.id.menu_forum -> {
                    setActionTitle(resources.getString(R.string.forum))
                    vp_main.currentItem = 2
                }
                R.id.menu_mine -> {
                    setActionTitle(resources.getString(R.string.mine))
                    vp_main.currentItem = 3
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        // ViewPager
        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        setActionTitle(resources.getString(R.string.notification))
                        bnv_main.menu.findItem(R.id.menu_notification).isChecked = true
                    }
                    1 -> {
                        setActionTitle(resources.getString(R.string.service))
                        bnv_main.menu.findItem(R.id.menu_service).isChecked = true
                    }
                    2 -> {
                        setActionTitle(resources.getString(R.string.forum))
                        bnv_main.menu.findItem(R.id.menu_forum).isChecked = true
                    }
                    3 -> {
                        setActionTitle(resources.getString(R.string.mine))
                        bnv_main.menu.findItem(R.id.menu_mine).isChecked = true
                    }
                }
            }

        })
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setActionTitle(title: String) {
        runOnUiThread {
            supportActionBar?.title = title
        }
    }

    // ViewPagerAdapter
    class ViewPagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> NotificationFragment()
                1 -> ServiceFragment()
                2 -> ForumFragment()
                3 -> MineFragment()
                else -> throw Exception("未知异常")
            }
        }

        override fun getCount(): Int {
            return 4
        }

    }

}