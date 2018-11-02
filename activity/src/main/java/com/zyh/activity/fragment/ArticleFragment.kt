package com.zyh.activity.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zyh.activity.R
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * 文章
 */
class ArticleFragment : Fragment() {

    private var paramPosition: Int? = -1
    private var mCurrentPosition = -2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramPosition = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // If activity recreated (such as from screen rotate), restore  //如果重新创建了活动(例如从屏幕旋转)，还原
        // the previous article selection set by onSaveInstanceState(). // onSaveInstanceState()设置的前一个文章选择。
        // This is primarily necessary when in the two-pane layout. //在双窗格布局中，这是必需的。
        if (savedInstanceState!=null){
            mCurrentPosition=savedInstanceState.getInt(ARG_POSITION)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(activity,"恢复onSaveInstanceState"+mCurrentPosition,Toast.LENGTH_SHORT).show()

        if (paramPosition!=-1){
            updateArticleView(paramPosition!!)
        }else if (mCurrentPosition!=-2){

            updateArticleView(mCurrentPosition)
        }
    }

    private fun updateArticleView(position: Int){
        textView.text=Ipsum.Articles[position]
        mCurrentPosition=position
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(activity,"保存onSaveInstanceState"+mCurrentPosition,Toast.LENGTH_SHORT).show()

        outState.putInt(ARG_POSITION,paramPosition!!)
    }

    companion object {
        internal const val ARG_POSITION = "position"

        @JvmStatic
        fun newInstance(position: Int) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position!!)
                }
            }
    }
}
