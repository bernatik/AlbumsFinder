package com.alexbernat.albumsfinder

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest

abstract class CoroutineTest {

    protected val testDispatcher = StandardTestDispatcher(TestCoroutineScheduler())

    protected fun runWithDispatcher(testAction: suspend () -> Unit) = runTest(testDispatcher) {
        testAction()
    }
}