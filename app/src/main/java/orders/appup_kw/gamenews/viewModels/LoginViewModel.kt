package orders.appup_kw.gamenews.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import orders.appup_kw.gamenews.network.BasicAuthClient
import orders.appup_kw.gamenews.network.MainNetwork
import retrofit2.Response

class LoginViewModel() : ViewModel() {

    private val _logining = MutableLiveData<Boolean>(false)

    val logining: LiveData<Boolean>
        get() = _logining

    private val _spiner = MutableLiveData<Boolean>(false)

    val spinner: LiveData<Boolean>
        get() = _spiner

    private val _textError = MutableLiveData<String>(null)

    val textError: LiveData<String>
        get() = _textError



    fun login(login: String, password: String){
        viewModelScope.launch {
            try {
                _textError.value = null
                _spiner.value = true
                val response: Response<ResponseBody> = BasicAuthClient<MainNetwork>(login, password).create(MainNetwork::class.java).login()
                if(response.code() == 200){
                    _logining.value = true
                }else{
                    _spiner.value = false
                    _textError.value = "Invalid password or login!!!"
                }
            } catch (error: Exception) {
                _textError.value = error.message
                _spiner.value = false
            }
        }


    }

}