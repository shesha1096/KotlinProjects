package com.shesha.projects.cmsapp.model

class Faq {
    var question: String? = null
    var answer: String? = null
    var isExpanded = false

    constructor() {}
    constructor(question: String?, answer: String?) {
        this.question = question
        this.answer = answer
        isExpanded = false
    }

}