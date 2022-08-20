package com.example.denettestapp.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.denettestapp.R
import com.example.denettestapp.presentation.ui.common.BaseActivity
import javax.inject.Inject

/**
 * TODO При входе в приложение доставать из SharedPreferences последний экран (вроде не надо)
 */
class MainActivity : BaseActivity(), NodeFragment.NavActivity {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NodeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToNode(nodeId: Int, isRoot: Boolean) {
        supportFragmentManager.commit {
            val destinationNode = NodeFragment.newInstance(nodeId, isRoot)
            replace(R.id.fragment_container, destinationNode)
            setReorderingAllowed(true)
        }
    }
}
