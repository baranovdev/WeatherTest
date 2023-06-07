package by.baranovdev.weathertest.ui.fragments.weather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.baranovdev.weathertest.R
import by.baranovdev.weathertest.databinding.FragmentWeatherBinding
import by.baranovdev.weathertest.ui.fragments.weather.epoxy.CityEpoxyController
import by.baranovdev.weathertest.ui.fragments.weather.epoxy.ForecastEpoxyController
import com.airbnb.mvrx.*

import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.bumptech.glide.Glide

class WeatherFragment : Fragment(R.layout.fragment_weather), MavericksView{
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherViewModel by fragmentViewModel()
    var cityEpoxyController: CityEpoxyController? = null
    var forecastEpoxyController: ForecastEpoxyController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false).apply {
            handler = this@WeatherFragment
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cityEpoxyController = CityEpoxyController(viewModel)
        forecastEpoxyController = ForecastEpoxyController()


        binding.tilSearch.editText?.doOnTextChanged { text, start, before, count ->
            if(!text.isNullOrEmpty() && text.length > 2) viewModel.searchCity(text.toString())
        }

        binding.ivSearch.setOnClickListener { viewModel.openSearch() }
        binding.rvForecast.layoutManager = GridLayoutManager(requireContext(), 3)
        if(cityEpoxyController != null) binding.rvSearchCities.setController(cityEpoxyController!!)
        if(forecastEpoxyController != null) binding.rvForecast.setController(forecastEpoxyController!!)

//        binding.rvForecast.layoutManager = getLayoutManager()
    }

    override fun invalidate(): Unit = withState(viewModel) { state ->
        if(state.weather.complete){
            binding.progressCircular.visibility = View.GONE
            binding.weather = state.weather.invoke()
            Glide.with(requireContext()).load(state.weather.invoke()?.current?.condition?.icon).override(120,120).into(binding.ivWeatherIcon)
        }
        if(!state.isSearchVisible) hideKeyboard()
        binding.mcvSearch.isVisible = state.isSearchVisible

        if(state.cities.complete){
            cityEpoxyController?.setData(state.cities.invoke())
        }
        if(state.forecast.complete){
            forecastEpoxyController?.setData(state.forecast.invoke())
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun getLayoutManager() = GridLayoutManager(requireContext(), 3)

}