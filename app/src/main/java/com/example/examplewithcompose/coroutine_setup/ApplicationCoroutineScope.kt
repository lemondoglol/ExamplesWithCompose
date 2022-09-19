package com.example.examplewithcompose.coroutine_setup

import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Tied to the lifetime of the application
 * */
class ApplicationCoroutineScope @Inject constructor(
    context: CoroutineContext
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = context
}
