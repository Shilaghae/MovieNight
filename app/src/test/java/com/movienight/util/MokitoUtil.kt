package com.movienight.util

import org.mockito.Mockito

inline fun <reified T : Any> mockT(): T = Mockito.mock(T::class.java)!!