package orders.appup_kw.gamenews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import orders.appup_kw.gamenews.R
import orders.appup_kw.gamenews.databinding.FragmentMainBinding
import orders.appup_kw.gamenews.model.News
import orders.appup_kw.gamenews.ui.adapters.NewsRecyclerViewAdapter
import orders.appup_kw.gamenews.viewModels.MainViewModel

class MainFragment : Fragment() {


    lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = arrayListOf<News>()
        news.add(News(0,
                "Xnjnjnj rtvr afegb egbetg fgbwggb wrtb wrtb wrb  wrtb wrtb wrtb wyb wwwwwwf",
                "sfg fgb",
                "12.23.2021",
                12,
                "https://ivan-pole.ru/wa-data/public/shop/products/87/10/1087/images/3365/3365.970x970.jpg"))

        //binding.list.layoutManager = RecyclerView.LayoutManager()
        val adapter = NewsRecyclerViewAdapter(context, news)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(context)

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

}