package com.zyh.views.recyclerview


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.zyh.views.R


/**
 * A simple [Fragment] subclass.
 *
 */
class SampleRecyclerViewFragment : Fragment() {

    private lateinit var currentLayoutManagerType: LayoutManagerType
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dataSet:Array<String>
    private lateinit var recyclerView:RecyclerView

    enum class LayoutManagerType { GRID_LAYOUT_MANAGER, LINEAR_LAYOUT_MANAGER }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataSet()
    }

    /**
     * 初始化数据
     */
    private fun initDataSet(){
        dataSet= Array(DATA_COUNT) { i -> "This is element # $i" }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false).apply { tag= TAG }
        recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        layoutManager=LinearLayoutManager(activity)

        currentLayoutManagerType=LayoutManagerType.LINEAR_LAYOUT_MANAGER

        if (savedInstanceState!=null){
            // Restore saved layout manager type.
            currentLayoutManagerType= savedInstanceState
                .getSerializable(KEY_LAYOUT_MANAGER) as LayoutManagerType
        }

        setRecycleViewLayoutManager(currentLayoutManagerType)

        // Set SampleCustomAdapter as the adapter for RecyclerView.
        recyclerView.adapter=SampleCustomAdapter(dataSet)

        rootView.findViewById<RadioButton>(R.id.linear_layout_rb).setOnClickListener {
            setRecycleViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER)
        }

        rootView.findViewById<RadioButton>(R.id.grid_layout_rb).setOnClickListener {
            setRecycleViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER)
        }
        return rootView
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    private fun setRecycleViewLayoutManager(layoutManagerType: LayoutManagerType){
        var scrollPosition = 0

        //如果已经设置了布局管理器，则获取当前滚动位置。
        if (recyclerView.layoutManager!=null){
            scrollPosition=(recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        when(layoutManagerType){
            SampleRecyclerViewFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER->{
                layoutManager=LinearLayoutManager(activity)
                currentLayoutManagerType=LayoutManagerType.LINEAR_LAYOUT_MANAGER
            }
            SampleRecyclerViewFragment.LayoutManagerType.GRID_LAYOUT_MANAGER->{
                layoutManager=GridLayoutManager(activity,SPAN_COUNT)
                currentLayoutManagerType=LayoutManagerType.GRID_LAYOUT_MANAGER
            }
        }

        with(recyclerView){
            layoutManager=this@SampleRecyclerViewFragment.layoutManager
            scrollToPosition(scrollPosition)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER,currentLayoutManagerType)
        super.onSaveInstanceState(savedInstanceState)
    }

    companion object {
        private const val TAG = "SampleRecyclerViewFragment"
        private const val DATA_COUNT = 60
        private const val SPAN_COUNT = 2
        private const val KEY_LAYOUT_MANAGER = "layoutManager"
    }
}
