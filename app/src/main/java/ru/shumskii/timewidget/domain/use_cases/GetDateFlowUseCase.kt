package ru.shumskii.timewidget.domain.use_cases

import kotlinx.coroutines.flow.Flow
import ru.shumskii.timewidget.data.repositories.DateRepository
import java.util.*
import javax.inject.Inject

class GetDateFlowUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {

    fun execute(): Flow<Date> {
        return dateRepository.date
    }

}