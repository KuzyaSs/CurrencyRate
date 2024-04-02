package ru.ermakov.currencyrate.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.ermakov.currencyrate.R
import ru.ermakov.currencyrate.databinding.ItemCurrencyRateBinding
import ru.ermakov.currencyrate.domain.model.CurrencyRate

class CurrencyRateAdapter :
    ListAdapter<CurrencyRate, CurrencyRateAdapter.CurrencyRateViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateViewHolder {
        return CurrencyRateViewHolder(
            ItemCurrencyRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrencyRateViewHolder, position: Int) {
        holder.bind(currencyRate = getItem(position))
    }

    inner class CurrencyRateViewHolder(
        private val binding: ItemCurrencyRateBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyRate: CurrencyRate) {
            binding.apply {
                textViewCharCode.text = currencyRate.charCode
                textViewName.text = currencyRate.name
                textViewRate.text = root.resources.getString(
                    R.string.rate,
                    currencyRate.rate
                )
                val rateChange = currencyRate.rate - currencyRate.previousRate
                textViewRateChange.text = if (rateChange >= 0)
                    root.resources.getString(
                        R.string.positiveRateChange,
                        String.format("%.3f", rateChange)
                    )
                else
                    root.resources.getString(
                        R.string.negativeRateChange,
                        String.format("%.3f", rateChange)
                    )
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CurrencyRate>() {
            override fun areItemsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate): Boolean {
                return oldItem.charCode == newItem.charCode
            }

            override fun areContentsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate): Boolean {
                return oldItem == newItem
            }
        }
    }
}