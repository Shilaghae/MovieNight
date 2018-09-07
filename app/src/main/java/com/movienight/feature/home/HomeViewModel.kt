package com.movienight.feature.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.movienight.base.RxModule
import com.movienight.data.Movie
import com.movienight.error.handleException
import com.movienight.service.PopularMovieService
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class HomeViewModel @Inject constructor(@Named(RxModule.ui) val uiScheduler: Scheduler, val popularMovieService: PopularMovieService) :
        ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    var loadingLiveDate: MutableLiveData<Boolean> = MutableLiveData()

    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun retrieveData() {
        compositeDisposable.add(popularMovieService.getMostPopularMovies()
                .observeOn(uiScheduler)
                .doOnSubscribe { loadingLiveDate.postValue(true) }
                .doOnError { loadingLiveDate.postValue(false) }
                .doOnComplete { loadingLiveDate.postValue(false) }
                .subscribe({ movies ->
                    moviesLiveData.postValue(movies)
                }, { error ->
                    error.fillInStackTrace()
                    Timber.d(error.message)
                    errorLiveData.postValue(handleException(error).error)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}