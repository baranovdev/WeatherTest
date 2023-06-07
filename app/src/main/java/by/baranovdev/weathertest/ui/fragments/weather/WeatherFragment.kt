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
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false).apply {
            handler = this@WeatherFragment
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setControllers()
        setListeners()
    }

    private fun setControllers() {
        cityEpoxyController = CityEpoxyController(viewModel)
        forecastEpoxyController = ForecastEpoxyController()
        if(cityEpoxyController != null) binding.rvSearchCities.setController(cityEpoxyController!!)
        if(forecastEpoxyController != null) binding.rvForecast.setController(forecastEpoxyController!!)
    }

    private fun setListeners() {
        binding.tilSearch.editText?.doOnTextChanged { text, start, before, count ->
            if (!text.isNullOrEmpty() && text.length > 2) viewModel.searchCity(text.toString())
        }
        binding.buttonBack.setOnClickListener {
            viewModel.hideSearch()
            hideKeyboard()
        }
        binding.ivSearch.setOnClickListener {
            viewModel.openSearch()
        }
        binding.rvForecast.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun invalidate(): Unit = withState(viewModel) { state ->
        if(state.weather.complete) updateWeatherUi(state)
        if(!state.isSearchVisible) hideKeyboard()
        if(state.cities.complete) updateCitiesUi(state)
        if(state.forecast.complete) updateForecastUi(state)
        setSearhVisibility(state)
    }

    private fun updateForecastUi(state: WeatherState) {
        binding.progressCircular.visibility = View.GONE
        binding.layoutContent.visibility = View.VISIBLE
        forecastEpoxyController?.setData(state.forecast.invoke())
    }

    private fun updateCitiesUi(state: WeatherState) {
        cityEpoxyController?.setData(state.cities.invoke())
    }

    private fun setSearhVisibility(state: WeatherState) {
        binding.mcvSearch.isVisible = state.isSearchVisible
    }

    private fun updateWeatherUi(state: WeatherState) {
        binding.progressCircular.visibility = View.GONE
        binding.layoutContent.visibility = View.VISIBLE
        binding.weather = state.weather.invoke()
        Glide.with(requireContext()).load(state.weather.invoke()?.current?.condition?.icon)
            .override(120, 120).into(binding.ivWeatherIcon)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}