package orders.appup_kw.gamenews.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import orders.appup_kw.gamenews.R
import orders.appup_kw.gamenews.databinding.FragmentMainBinding
import orders.appup_kw.gamenews.model.News
import orders.appup_kw.gamenews.network.POJONews
import orders.appup_kw.gamenews.ui.adapters.NewsRecyclerViewAdapter
import orders.appup_kw.gamenews.viewModels.MainViewModel
import java.util.ArrayList

class MainFragment : Fragment() {


    lateinit var adapter: NewsRecyclerViewAdapter
    lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel
    var news: MutableList<POJONews?> = ArrayList()

    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var k: Int = 0
    private val pagingSize = 3
    private var loading = false
    private var allLoading = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel =  ViewModelProvider(requireActivity()).get(MainViewModel::class.java)



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNews(k, 3)
        k+=pagingSize

        adapter = NewsRecyclerViewAdapter(context, news)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(context)
        setRecyclerViewScrollListener()

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_loginFragment)
        }

        viewModel.reading.observe(requireActivity()) {value->
            value.let {
                println("lolol ${it[0].id}")
                reloadNews(it)
            }
        }
    }

    private fun reloadNews(news: List<POJONews>){
        if(news.isNotEmpty()) {

            if(k != pagingSize) {
                this.news.removeAt(this.news.size - 1)
            }
            this.news.addAll(news)


            adapter.notifyDataSetChanged()
            loading = true

        }else{
            this.news.removeLast()
            //this.news.removeAt(this.news.size - 1)
            adapter.notifyDataSetChanged()
            allLoading = false
        }
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(loading && allLoading) {

                    val totalItemCount = binding.list.layoutManager?.itemCount
                    val lastVisibleItemPosition: Int =
                            (binding.list.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    Log.d("MyTAG", "Load new list $totalItemCount==${lastVisibleItemPosition + 1}")
                    if (totalItemCount?.minus(2)!! < lastVisibleItemPosition) {

                        viewModel.getNews(k, 3)
                        k += pagingSize

                        news.add(null)
                        adapter.notifyDataSetChanged()

                        loading = false
                    }
                }

            }
        }
        binding.list.addOnScrollListener(scrollListener)
    }

}