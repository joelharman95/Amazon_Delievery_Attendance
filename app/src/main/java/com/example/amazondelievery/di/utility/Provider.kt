package com.example.amazondelievery.di.utility

abstract class Provider<T> {

    @Volatile
    var instance: T? = null

    protected abstract fun create(arg: Array<out Any> = emptyArray()): T

    /**
     *
    Double checked locking and synchronized algorithms for singleton pattern
     *
     **/

    fun get(vararg arg: Any) = instance ?: synchronized(this) {
        instance ?: create(arg).also { instance = it }
    }

}