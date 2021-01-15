package orders.appup_kw.gamenews.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import orders.appup_kw.gamenews.repository.NewsFeedRepository

class MainViewModel @ViewModelInject constructor(private val postsRepository: NewsFeedRepository)
    : ViewModel() {


}