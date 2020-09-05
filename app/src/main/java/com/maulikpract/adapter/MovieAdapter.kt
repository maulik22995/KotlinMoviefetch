package com.maulikpract.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.maulikpract.BR
import com.maulikpract.R
import com.maulikpract.databinding.ItemMovieBinding
import com.maulikpract.model.Result
import com.maulikpract.utils.OnItemClickListner


class MovieAdapter(
    private val movieData: ArrayList<Result>,
    private val listener: OnItemClickListner
) : RecyclerView.Adapter<MovieAdapter.MoviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewViewHolder {
        return MoviewViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    override fun onBindViewHolder(holder: MoviewViewHolder, position: Int) {
        holder.bind(movieData[position])
        (holder.viewBinding as ItemMovieBinding).btnDownload.setOnClickListener {
            listener.onItemClick(it, movieData[position], position)
        }
    }

    override fun onBindViewHolder(
        holder: MoviewViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.firstOrNull() != null) {
            with(holder.itemView) {
                (payloads.first() as Bundle).getInt("progress").also {
                    movieData[position].progress = it
                    (holder.viewBinding as ItemMovieBinding).progressBar.isVisible = it < 99
                    (holder.viewBinding as ItemMovieBinding).tvProgress.isVisible = it < 99
                    (holder.viewBinding as ItemMovieBinding).tvProgress.text = "$it %"
                }
            }
        }
    }

    fun setDownloading(data: Result, isDownloading: Boolean) {
        getDummy(data)?.isDownloading = isDownloading
        notifyItemChanged(movieData.indexOf(data))
    }

    fun setProgress(data: Result, progress: Int) {
        getDummy(data)?.progress = progress
        notifyItemChanged(movieData.indexOf(data), Bundle().apply { putInt("progress", progress) })
    }

    private fun getDummy(data: Result) = movieData.find { data.id == it.id }



    fun setData(data: ArrayList<Result>) {
        movieData.addAll(data)
        notifyDataSetChanged()
    }

    class MoviewViewHolder(val viewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(moview: Result) {
            viewBinding.setVariable(BR.dataModel, moview)
            viewBinding.executePendingBindings()
        }
    }
}