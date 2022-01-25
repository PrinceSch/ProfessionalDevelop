package com.princesch.profdevelop.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : ISchedulerProvider {
    override fun userInterface(): Scheduler = AndroidSchedulers.mainThread()
    override fun inputOutput(): Scheduler = Schedulers.io()
}