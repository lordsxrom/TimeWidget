package ru.shumskii.timewidget.domain.use_cases

import ru.shumskii.timewidget.data.repositories.DateRepository
import java.util.*
import javax.inject.Inject

class UpdateDateUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {

    fun execute() {
        val actualDate = Date()
        dateRepository.setDate(actualDate)
    }

}