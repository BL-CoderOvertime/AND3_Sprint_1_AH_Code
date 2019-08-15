package com.atob.intentdemo.Models

import java.io.Serializable

data class Book(var title:String, var author:String = "Unknown", var publicationDate:Long = 0, var isWatched:Boolean = false):Serializable