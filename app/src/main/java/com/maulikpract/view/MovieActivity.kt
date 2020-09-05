package com.maulikpract.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.maulikpract.R
import com.maulikpract.adapter.MovieAdapter
import com.maulikpract.base.BaseActivity
import com.maulikpract.databinding.ActivityMovieListBinding
import com.maulikpract.model.DownloadResult
import com.maulikpract.model.Result
import com.maulikpract.utils.OnItemClickListner
import com.maulikpract.utils.downloadFile
import io.ktor.client.HttpClient
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity :
    BaseActivity<ActivityMovieListBinding, MovieViewModel, MovieState>(R.layout.activity_movie_list),
    OnItemClickListner {

    private lateinit var moviewAdapter: MovieAdapter
    private var data = ArrayList<Result>()
//    val ktor: HttpClient by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        viewModel.fetchMovieData()
    }

    private fun initViews() {
        moviewAdapter = MovieAdapter(data, this)
        reyData.apply {
            adapter = moviewAdapter
        }
    }

    override val viewModel: MovieViewModel by viewModel()
    override fun observeViewState(state: MovieState) {
        state.movieApiState.observe(this) {
            when (it) {
                is MovieState.MovieApiState.Success -> {
                    it.data?.let {
                        data = it.results
                        moviewAdapter.setData(data)
                    }
                }

                is MovieState.MovieApiState.Error -> {
                    toast(it.th.localizedMessage)
                }
            }
        }
    }

    override fun onItemClick(view: View, data: Any, position: Int) {
        val movie = data as Result
        when {
            movie.isDownloading -> {

            }
            else -> {
                try {
                    downloadWithFlow(movie)
                } catch (e: Exception) {
                    //generic error while downloading
                }
            }
        }
    }

    private fun downloadWithFlow(movie: Result) {
       /* CoroutineScope(Dispatchers.IO).launch {

            ktor.downloadFile(
                movie.file,
                "${resources.getString(R.string.image_path)}${movie.poster_path}"
            ).collect {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is DownloadResult.Success -> {
                            moviewAdapter.setDownloading(movie, false)
                        }
                        is DownloadResult.Error -> {
                            moviewAdapter.setDownloading(movie, false)
                            toast("Error while downloading ${movie.title}")
                        }
                        is DownloadResult.Progress -> {
                            moviewAdapter.setProgress(movie, it.progress)
                        }
                    }
                }
            }
        }*/
    }
}