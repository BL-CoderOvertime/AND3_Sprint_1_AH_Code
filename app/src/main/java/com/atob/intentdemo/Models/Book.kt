package com.atob.intentdemo.Models

import java.io.Serializable

data class Book(var title:String, var author:String = "Unknown", var isWatched:Boolean = false):Serializable