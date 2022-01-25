package com.princesch.profdevelop.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun userInterface(): Scheduler
    fun inputOutput(): Scheduler
}