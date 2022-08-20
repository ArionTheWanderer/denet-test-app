package com.example.denettestapp.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.example.denettestapp.R
import com.example.denettestapp.data.common.DataState
import com.example.denettestapp.databinding.FragmentNodeBinding
import com.example.denettestapp.presentation.ui.common.BaseFragment
import com.example.denettestapp.presentation.model.tree.Node
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM_ID = "id"
private const val ARG_PARAM_NAME = "name"

class NodeFragment : BaseFragment() {
    private var nodeId = 0L
    private var nodeName: String = "root"
    private var navActivity: NavActivity? = null
    private var binding: FragmentNodeBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NodeViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        injector.inject(this)
        super.onAttach(context)
        try {
            navActivity = requireActivity() as NavActivity
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity has to implement an interface NodeFragment.NavActivity")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            nodeId = savedInstanceState.getLong(ARG_PARAM_ID)
            savedInstanceState.getString(ARG_PARAM_NAME)?.let {
                nodeName = it
            }
        } else if (arguments != null) {
            val args = arguments as Bundle
            nodeId = args.getLong(ARG_PARAM_ID)
            args.getString(ARG_PARAM_NAME)?.let {
                nodeName = it
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
        FragmentNodeBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNodeBinding.bind(view)
        setupToolbar()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNode(nodeId)
                viewModel.node.collect { node ->
                    when (node) {
                        is DataState.Init -> {}
                        is DataState.Loading -> {}
                        is DataState.Data -> {
                            updateUi(node.data)
                        }
                        is DataState.Error -> {
                            showErrorMessage(node.error)
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { errorMessage ->
                    when (errorMessage) {
                        "" -> {}
                        else -> showErrorMessage(errorMessage)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isDeleted.collect { isDeleted ->
                    when (isDeleted) {
                        false -> {}
                        true ->  {
                            val parentNode = (viewModel.node.value as? DataState.Data)?.data?.parent
                            if (parentNode != null) {
                                navActivity?.navigateToNode(parentNode.id, parentNode.name)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(ARG_PARAM_ID, nodeId)
        outState.putString(ARG_PARAM_NAME, nodeName)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        navActivity = null
    }

    private fun setupToolbar() {
        binding?.toolbarNode?.title = nodeName
        binding?.toolbarNode?.inflateMenu(R.menu.menu_node)
        binding?.toolbarNode?.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_create_node) {
                viewLifecycleOwner.lifecycleScope.launch {
                    whenStarted {
                        viewModel.createNode()
                    }
                }
                return@setOnMenuItemClickListener true
            } else if (item.itemId == R.id.action_delete_node) {
                viewLifecycleOwner.lifecycleScope.launch {
                    whenStarted {
                        viewModel.deleteNode()
                    }
                }
                return@setOnMenuItemClickListener true
            }
            true
        }
    }

    private fun updateUi(node: Node) {
        binding?.llNodeParent?.removeAllViews()
        binding?.llNodeChildren?.removeAllViews()
        binding?.toolbarNode?.menu?.findItem(R.id.action_create_node)?.isEnabled = true
        if (node.parent != null) {
            binding?.toolbarNode?.menu?.findItem(R.id.action_delete_node)?.isEnabled = true
            val button = Button(requireContext())
            button.text = node.parent.name
            button.setOnClickListener {
                navActivity?.navigateToNode(node.parent.id, node.parent.name)
            }
            binding?.llNodeParent?.addView(button, LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            )
        }
        for (childNode in node.children) {
            val button = Button(requireContext())
            button.text = childNode.name
            button.setOnClickListener {
                navActivity?.navigateToNode(childNode.id, childNode.name)
            }
            binding?.llNodeChildren?.addView(button, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            )
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        viewModel.clearError()
    }

    companion object {
        @JvmStatic
        fun newInstance(nodeId: Long, nodeName: String) =
                NodeFragment().apply {
                    arguments = Bundle().apply {
                        putLong(ARG_PARAM_ID, nodeId)
                        putString(ARG_PARAM_NAME, nodeName)
                    }
                }
    }

    interface NavActivity {
        fun navigateToNode(nodeId: Long, nodeName: String)
    }
}
