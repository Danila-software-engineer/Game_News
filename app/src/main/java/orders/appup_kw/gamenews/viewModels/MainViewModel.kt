package orders.appup_kw.gamenews.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import orders.appup_kw.gamenews.network.BasicAuthClient
import orders.appup_kw.gamenews.network.MainNetwork
import orders.appup_kw.gamenews.network.POJONews
import orders.appup_kw.gamenews.repository.NewsFeedRepository
import retrofit2.Response
import java.util.ArrayList

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(private val postsRepository: NewsFeedRepository)
    : ViewModel() {

    private val _reading = MutableLiveData<List<POJONews>>()

    val reading: LiveData<List<POJONews>>
        get() = _reading


    fun getNews(id1: Int?, id2: Int?){
        viewModelScope.launch {
            try {

                _reading.value = postsRepository.getNews(id1,id2)

            } catch (error: Exception) {

            }
        }

    }


}