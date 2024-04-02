package ru.ermakov.currencyrate.presentation.screen.currencyRates

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import ru.ermakov.currencyrate.CurrencyRateApplication
import ru.ermakov.currencyrate.R
import ru.ermakov.currencyrate.databinding.ActivityCurrencyRatesBinding
import ru.ermakov.currencyrate.presentation.adapter.CurrencyRateAdapter
import javax.inject.Inject

class CurrencyRateActivity : AppCompatActivity() {
    private var _binding: ActivityCurrencyRatesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var currencyRateViewModelFactory: CurrencyRateViewModelFactory
    private val currencyRateViewModel: CurrencyRateViewModel by viewModels {
        currencyRateViewModelFactory
    }

    private var currencyRateAdapter: CurrencyRateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCurrencyRatesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as CurrencyRateApplication).applicationComponent.inject(activity = this)
        setUpCurrencyRatesRecyclerView()
        setUpListeners()
        setUpObserver()
    }

    private fun setUpCurrencyRatesRecyclerView() {
        currencyRateAdapter = CurrencyRateAdapter()
        binding.recyclerViewCurrentRates.adapter = currencyRateAdapter
    }

    private fun setUpListeners() {
        binding.buttonTryAgain.setOnClickListener {
            currencyRateViewModel.loadCurrencyRates()
        }
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                currencyRateViewModel.state.collect { state ->
                    setUpTitle(lastUpdateDate = state.lastUpdateDate)
                    currencyRateAdapter?.submitList(state.currencyRates)
                    setUpLoading(
                        isProgressIndicatorShown = state.isLoading && state.currencyRates.isEmpty()
                    )
                    setUpError(
                        isError = state.isError,
                        isCurrencyRateListEmpty = state.currencyRates.isEmpty()
                    )
                }
            }
        }
    }

    private fun setUpTitle(lastUpdateDate: String?) {
        if (lastUpdateDate != null) {
            binding.textViewTitle.text = resources.getString(
                R.string.currency_rate_with_date, lastUpdateDate
            )
        }
    }

    private fun setUpLoading(isProgressIndicatorShown: Boolean) {
        binding.progressIndicator.isVisible = isProgressIndicatorShown
    }

    private fun setUpError(isError: Boolean, isCurrencyRateListEmpty: Boolean) {
        binding.linearLayoutError.isVisible = isError && isCurrencyRateListEmpty
        if (isError && !isCurrencyRateListEmpty) {
            showSnackBar(message = resources.getString(R.string.connection_failure_exception))
            currencyRateViewModel.clearError()
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}