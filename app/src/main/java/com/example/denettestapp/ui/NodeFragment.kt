package com.example.denettestapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.denettestapp.R

private const val ARG_PARAM_ID = "id"

/**
 * Во фрагмент передается параметр с помощью фабричного метода
 * Параметр сразу сохраняется во вьюмодели
 * При пересоздании фрагмента, он проверяет, есть ли у вьюмодели параметр
 */
class NodeFragment : Fragment() {
    private var nodeId = 0
    private var isRoot: Boolean = true
    private var childCount: Int = 0
    private val viewModel: NodeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nodeId = it.getInt(ARG_PARAM_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // TODO Inflate the layout for this fragment
//        val layout = inflater.inflate(R.layout.fragment_node, container, false)
//        if (!isRoot) {
//
//        }
        return inflater.inflate(R.layout.fragment_node, container, false)
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
        fun newInstance(nodeId: Int) =
                NodeFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM_ID, nodeId)
                    }
                }
    }

    interface NavActivity {
        fun navigateToNode(nodeId: Int)
    }
}
