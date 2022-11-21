package com.gitrepotestapp.db.mapper

interface Mapper<In, Out> {
    fun mapTo(inValue: In): Out
}
