package orders.appup_kw.gamenews.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import orders.appup_kw.gamenews.network.POJONews
import orders.appup_kw.gamenews.repository.NewsFeedRepository

@ExperimentalCoroutinesApi
class NewsViewModel
@ViewModelInject constructor(private val postsRepository: NewsFeedRepository): ViewModel() {

    private val _reading = MutableLiveData<POJONews>(null)

    val reading: LiveData<POJONews>
        get() = _reading

    private val _spiner = MutableLiveData<Boolean>(false)

    val spinner: LiveData<Boolean>
        get() = _spiner


    fun read(id: Int) {
        viewModelScope.launch {
            try {
                _spiner.value = true
                _reading.value = postsRepository.readNews(id)
            } catch (error: Exception) {
                println("lolol $error")
            }finally {
                _spiner.value = false
            }
        }


    }
}