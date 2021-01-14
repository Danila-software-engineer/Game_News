package orders.appup_kw.gamenews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import orders.appup_kw.gamenews.R
import orders.appup_kw.gamenews.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_createFragment)
        }
    }

}