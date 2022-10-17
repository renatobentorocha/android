package com.example.jettrivia.repository

import com.example.jettrivia.data.DataOrException
import com.example.jettrivia.model.QuestionItem
import com.example.jettrivia.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionAPI) {
    private val dataOrException = DataOrException<
            ArrayList<QuestionItem>,
            Boolean,
            Exception
            >()

    suspend fun questions() : DataOrException<
            ArrayList<QuestionItem>,
            Boolean,
            Exception
            > {
                try {
                    dataOrException.loading = true
                    dataOrException.data = api.all()
                } catch (e: Exception) {
                    dataOrException.e = e
                } finally {
                    dataOrException.loading = false
                }

                return dataOrException
            }
}