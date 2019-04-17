package br.edu.ifsp.scl.movievolley

interface BindableFragment {

    fun <TYPE> bind(model: TYPE)
}