package com.example.denettestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.example.denettestapp.R

/**
 * TODO При входе в приложение доставать из SharedPreferences последний экран (вроде не надо)
 */
class MainActivity : AppCompatActivity(), NodeFragment.NavActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToNode(nodeId: Int) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)

        }
    }
}
