package orders.appup_kw.gamenews.viewModels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import orders.appup_kw.gamenews.repository.NewsFeedRepository
import java.io.InputStream

@ExperimentalCoroutinesApi
class CreateViewModel
@ViewModelInject constructor(private val postsRepository: NewsFeedRepository): ViewModel() {

    private val _creating = MutableLiveData<Boolean>(false)

    val creating: LiveData<Boolean>
        get() = _creating

    private val _spiner = MutableLiveData<Boolean>(false)

    val spinner: LiveData<Boolean>
        get() = _spiner



    fun create(inputStream: InputStream,text:String,name:String){
        viewModelScope.launch {
            try {
                _spiner.value = true
                val post = "{ \"text\":\"$text\",\"name\":\"$name\",\"data\":\"19.10.2020\"}"
                println("lolol ")
                val code = postsRepository.createNews(inputStream,post)
                println("lolol $code")
                if(code == 201)
                {
                    _creating.value = true
                }else{
                    _spiner.value = false
                }
            } catch (error: Exception) {
                println("lolol $error")
                _spiner.value = false
            }
        }



    }


}