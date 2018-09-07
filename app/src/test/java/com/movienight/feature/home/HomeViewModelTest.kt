package com.movienight.feature.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.movienight.data.Movie
import com.movienight.error.ErrorMatcher
import com.movienight.service.TopRatedMovieService
import com.movienight.util.mockT
import com.movienight.util.LifecycleOwnerUtil.LIFECYCLE_OWNER_UTIL
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.net.UnknownHostException

class HomeViewModelTest {

    // Executes each task synchronously using Architecture Components. For more information {@link https://github.com/googlesamples/android-architecture}
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    var topRatedMovieService = mock(TopRatedMovieService::class.java)
    var observable: BehaviorSubject<List<Movie>> = BehaviorSubject.create()
    val homeViewModel: HomeViewModel = HomeViewModel(Schedulers.trampoline(), topRatedMovieService)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testNoMovieReturned() {
        `when`(topRatedMovieService.getTopRatedMovies()).thenReturn(observable)

        val observer: Observer<List<Movie>> = mockT()

        homeViewModel.retrieveData()

        homeViewModel.moviesLiveData.observe(LIFECYCLE_OWNER_UTIL, observer)

        val emptyMovies = listOf<Movie>()

        observable.onNext(emptyMovies)

        verify(observer).onChanged(emptyMovies)
    }

    @Test
    fun testTwoMoviesReturned() {
        `when`(topRatedMovieService.getTopRatedMovies()).thenReturn(observable)

        val observer: Observer<List<Movie>> = mockT()

        homeViewModel.retrieveData()

        homeViewModel.moviesLiveData.observe(LIFECYCLE_OWNER_UTIL, observer)

        val movie1 = Movie("Movie 1", id = 1)

        val movie2 = Movie("Movie 2", id = 2)

        val movies = listOf(movie1, movie2)

        observable.onNext(movies)

        verify(observer).onChanged(movies)
    }

    @Test
    fun testAHttpErrorReturnsWhileRetrievingTheMovies() {
        `when`(topRatedMovieService.getTopRatedMovies()).thenReturn(observable)

        val observer: Observer<String> = mockT()

        homeViewModel.retrieveData()

        homeViewModel.errorLiveData.observe(LIFECYCLE_OWNER_UTIL, observer)

        val httpException: HttpException = mock(HttpException::class.java)

        `when`(httpException.code()).thenReturn(403)

        observable.onError(httpException)

        verify(observer).onChanged(ErrorMatcher.FORBIDDEN.error)
    }

    @Test
    fun testAnUnknownHostExceptionReturnsWhileRetrievingTheMovies() {
        `when`(topRatedMovieService.getTopRatedMovies()).thenReturn(observable)

        val observer: Observer<String> = mockT()

        homeViewModel.retrieveData()

        homeViewModel.errorLiveData.observe(LIFECYCLE_OWNER_UTIL, observer)

        val unknownHostException = UnknownHostException()

        observable.onError(unknownHostException)

        verify(observer).onChanged(ErrorMatcher.NO_CONNECTION.error)
    }

    @Test
    fun testAGeneralErrorExceptionReturnsWhileRetrievingTheMovies() {
        `when`(topRatedMovieService.getTopRatedMovies()).thenReturn(observable)

        val observer: Observer<String> = mockT()

        homeViewModel.retrieveData()

        homeViewModel.errorLiveData.observe(LIFECYCLE_OWNER_UTIL, observer)

        val exception = Exception()

        observable.onError(exception)

        verify(observer).onChanged(ErrorMatcher.GENERAL_ERROR.error)
    }
}
