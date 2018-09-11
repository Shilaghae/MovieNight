package com.movienight.feature.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.movienight.base.NetworkWatcher
import com.movienight.base.RxModule
import com.movienight.data.Movie
import com.movienight.database.MovieDatabase
import com.movienight.error.handleException
import com.movienight.service.TopRatedMovieService
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class HomeViewModel @Inject constructor(@Named(RxModule.ui) val uiScheduler: Scheduler,
        val topRatedMovieService: TopRatedMovieService,
        val movieDatabase: MovieDatabase, val networkWatcher: NetworkWatcher) :
        ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var repositoryMovieLiveData: LiveData<List<Movie>> = movieDatabase.movieDao().getAllMovies()

    var loadingLiveDate: MutableLiveData<Boolean> = MutableLiveData()

    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    var onMovieClickedLiveData: MutableLiveData<Movie> = MutableLiveData()

    fun retrieveData() {

        compositeDisposable.add(networkWatcher.onConnectionChanged()
                .subscribe({
                    if (it) {
                        topRatedMovieService.getTopRatedMovies()
                                .doOnNext({
                                    movieDatabase.movieDao().deleteAllMovies()
                                })
                                .doOnNext({ movies ->
                                    Observable.fromIterable(movies).subscribe({
                                        movieDatabase.movieDao().insert(it)
                                    })
                                })
                                .observeOn(uiScheduler)
                                .doOnSubscribe { loadingLiveDate.postValue(true) }
                                .doOnError { loadingLiveDate.postValue(false) }
                                .doOnComplete { loadingLiveDate.postValue(false) }
                                .subscribe({}, { error ->
                                    error.fillInStackTrace()
                                    Timber.d(error)
                                    errorLiveData.postValue(handleException(error).error)
                                })
                    }
                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getTopRatedMovieService() {
        compositeDisposable.add(topRatedMovieService.getTopRatedMovies()
                .doOnNext({
                    movieDatabase.movieDao().deleteAllMovies()
                })
                .doOnNext({ movies ->
                    Observable.fromIterable(movies).subscribe({
                        movieDatabase.movieDao().insert(it)
                    })
                })
                .observeOn(uiScheduler)
                .subscribe({}, { error ->
                    errorLiveData.postValue(handleException(error).error)
                }))
    }
}