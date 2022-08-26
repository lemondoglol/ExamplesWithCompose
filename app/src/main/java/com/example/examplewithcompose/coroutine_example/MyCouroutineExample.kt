package com.example.examplewithcompose.coroutine_example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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