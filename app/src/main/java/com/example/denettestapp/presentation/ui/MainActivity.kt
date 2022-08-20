package com.example.denettestapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.commit
import com.example.denettestapp.R
import com.example.denettestapp.presentation.ui.common.BaseActivity

class MainActivity : BaseActivity(), NodeFragment.NavActivity {

    private var wasNotOpenedYet = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            wasNotOpenedYet = savedInstanceState.getBoolean(ARG_WAS_NOT_OPENED_YET)
        }
        if (wasNotOpenedYet) {
            supportFragmentManager.commit {
                val destinationNode = NodeFragment.newInstance(1, "root")
                replace(R.id.fragment_container, destinationNode)
                setReorderingAllowed(true)
            }
            wasNotOpenedYet = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(ARG_WAS_NOT_OPENED_YET, wasNotOpenedYet)
    }

    override fun navigateToNode(nodeId: Long, nodeName: String) {
        supportFragmentManager.commit {
            val destinationNode = NodeFragment.newInstance(nodeId, nodeName)
            replace(R.id.fragment_container, destinationNode)
            setReorderingAllowed(true)
        }
    }

    companion object {
        private const val ARG_WAS_NOT_OPENED_YET = "was_not_opened_yet"
    }
}
