package com.example.bigi.kinotop.data

import com.google.gson.annotations.SerializedName

data class NewFilm(

		@field:SerializedName("dates")
	val dates: Dates? = null,

		@field:SerializedName("page")
	val page: Int? = null,

		@field:SerializedName("total_pages")
	val totalPages: Int? = null,

		@field:SerializedName("results")
	val results: List<NewFilmData?>? = null,

		@field:SerializedName("total_results")
	val totalResults: Int? = null
)