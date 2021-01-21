package orders.appup_kw.gamenews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import orders.appup_kw.gamenews.R
import orders.appup_kw.gamenews.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {


    lateinit var binding: FragmentCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabCreate.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_createFragment_to_newsFragment2)
        }
    }

}