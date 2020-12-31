package com.shesha.projects.kotlinapplication

class MovieModel {
    var movieTitle: String? = null
    var movieDescription: String? = null

    constructor() {}
    constructor(movieTitle: String?, movieDescription: String?) {
        this.movieTitle = movieTitle
        this.movieDescription = movieDescription
    }
}