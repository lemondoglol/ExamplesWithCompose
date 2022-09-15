package com.example.examplewithcompose.coroutine_example

import android.util.Log
import kotlinx.coroutines.*

/**
 * best practice if you want to make your coroutine run in sequence
 *  - all the nested called function inside scope does not launch another coroutine inside
 * */
fun runCoroutineInSequence() {
    CoroutineScope(Dispatchers.IO).launch {
        // everything inside will run in sequence in this block except funThatLaunchAnotherScope,
        // it might run before or after another function
        funThatLaunchAnotherScope()
        normalFun()
        suspendFun()
    }
}

private fun normalFun() = print("Something in normal")

private suspend fun suspendFun() = print("Something in suspend")

private fun funThatLaunchAnotherScope() {
    CoroutineScope(Dispatchers.IO).launch {
        normalFun()
    }
}

/**
 * how do we make funThatLaunchAnotherScope() also run in sequence
 * - we just pull out the implementation out from launchScope of funThatLaunchAnotherScope() into
 *      another function, and then call this new function inside runCoroutineInSequence1, so it only
 *      has one scope
 * - funLaunchAnotherScope still same as and can be used as before, no logic being changed for this
 * */
fun runCoroutineInSequence1() {
    CoroutineScope(Dispatchers.IO).launch {
        // everything inside will run in sequence in this block except funThatLaunchAnotherScope,
        // it might run before or after another function
        funLaunchAnotherScopeContent()
        normalFun()
        suspendFun()
    }
}

// this will keep the same as before
private fun funLaunchAnotherScope() {
    CoroutineScope(Dispatchers.IO).launch {
        funLaunchAnotherScopeContent()
    }
}

private fun funLaunchAnotherScopeContent() {
    // do something here
    normalFun()
}

/**
 * following is for .launch and .async
 * */
val supervisorJob = SupervisorJob()
val myScope = CoroutineScope(Dispatchers.IO + supervisorJob + CoroutineName("myOwnScope"))

fun launchVSAsync() {
    myScope.launch {
//        Log.d("Tuna", this.coroutineContext.toString())
        doSomeV1()
        Log.d("Tuna", "+++++++++")
        doSomeV2()
    }
}

private suspend fun doSomeV1() = withContext(Dispatchers.IO) {
    val jobLst = mutableListOf<Deferred<Int>>()
    for (index in 0..5) { // no bug here
        jobLst.add(
            async {
//                Log.d("Tuna", this.coroutineContext.toString())
                mockAPICall(index)
            }
        )
    }
    jobLst.awaitAll().onEach {
        Log.d("Tuna", it.toString())
    }
}

private suspend fun doSomeV2() = withContext(Dispatchers.IO) {
    val jobLst = mutableListOf<Deferred<Int>>()
    var index = 0
    while (index <= 5) { // this is causing the issue; for loop is okay
        jobLst.add(
            async {
                mockAPICall(index)
            }
        )
        index += 1
    }

    jobLst.awaitAll().onEach {
        Log.d("Tuna", it.toString())
    }
}

private suspend fun mockAPICall(id: Int): Int {
    delay(1000L)
    return id
}