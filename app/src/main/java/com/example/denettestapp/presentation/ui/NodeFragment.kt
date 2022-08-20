package com.example.denettestapp.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.denettestapp.R
import com.example.denettestapp.databinding.FragmentNodeBinding
import com.example.denettestapp.presentation.ui.common.BaseFragment

private const val ARG_PARAM_ID = "id"
private const val ARG_PARAM_NAME = "name"
private const val ARG_PARAM_IS_ROOT = "root"

/**
 * Во фрагмент передается параметр с помощью фабричного метода
 * Параметр сразу сохраняется во вьюмодели
 * При пересоздании фрагмента, он проверяет, есть ли у вьюмодели параметр
 */
class NodeFragment : BaseFragment() {
    private var nodeId = 0L
    private var nodeName: String = "root"

    private var navActivity: NavActivity? = null
    private var binding: FragmentNodeBinding? = null
    // TODO оставить в активити или нет?
    private val viewModel: NodeViewModel by activityViewModels()

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
                              savedInstanceState: Bundle?): View? {
        // TODO Inflate the layout for this fragment
        return FragmentNodeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNodeBinding.bind(view)
        binding?.toolbarNode?.title = nodeId.toString()
        binding?.toolbarNode?.inflateMenu(R.menu.menu_node)
//        if (!isRoot) binding?.toolbarNode?.menu?.findItem(R.id.action_delete_node)?.isEnabled = true
        binding?.toolbarNode?.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_create_node) {
                // TODO
            } else if (item.itemId == R.id.action_delete_node) {
                // TODO
            }
            true
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param nodeId Id of node in tree.
         * @return A new instance of fragment NodeFragment.
         */
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
