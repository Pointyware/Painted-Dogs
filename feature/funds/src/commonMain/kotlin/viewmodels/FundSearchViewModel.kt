package org.pointyware.painteddogs.feature.collections.core.viewmodels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.core.entities.usDollars
import org.pointyware.painteddogs.feature.collections.core.interactors.SearchCollectionsUseCase

/**
 * ViewModel for searching donations/collections.
 */
interface FundSearchViewModel {
    val state: StateFlow<CollectionSearchUiState>
    /**
     * Called when the search query changes.
     */
    fun onSearchQueryChanged(query: String)
    /**
     * Called when the search query is submitted.
     */
    fun onSubmitQuery(query: String)
}

/**
 *
 */
class FundSearchViewModelImpl(
    private val searchCollectionsUseCase: SearchCollectionsUseCase,
    // TODO: Fund->CollectionUiStateMapper impls for different contexts
): FundSearchViewModel {
    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow(CollectionSearchUiState.EMPTY)
    override val state: StateFlow<CollectionSearchUiState>
        get() = _state

    override fun onSearchQueryChanged(query: String) {
        _state.update { it.copy(query = query) }
    }

    class JobContainer(
        val scope: CoroutineScope
    ) {

        private var job: Job? = null
        private var lastKeys: List<Any>? = null
        fun cancelAndLaunch(vararg keys: Any, block: suspend CoroutineScope.()->Unit) {
            val newKeys = keys.toList()
            if (newKeys != lastKeys) return
            lastKeys = newKeys

            job?.cancel(message = "Job Restarting")
            job = scope.launch(block = block)
        }
    }

    private var queryJobContainer: JobContainer = JobContainer(viewModelScope)
    override fun onSubmitQuery(query: String) {
        _state.value = CollectionSearchUiState(query, true, emptyList())
        queryJobContainer.cancelAndLaunch(query) {
            val fundList = searchCollectionsUseCase.invoke(query).getOrNull() ?: emptyList()
            val uiList = fundList.map { fund ->
                val currentAmount = 0L.usDollars() // fund.donations.sumOf { it.amount }
                val target = fund.target ?: 0L.usDollars()
                val progress = if (currentAmount >= target) target else currentAmount
                val percentage = progress.amount * 100f / target.amount + 0.5f

                CollectionUiState(
                    id = fund.id,
                    title = fund.title,
                    description = fund.description,
                    progress = ProgressUiState(
                        currentAmount.amount.toString(),
                        progress.amount.toString(),
                        percentage
                    ),
                )
            }

            _state.update {
                it.copy(loading = false, result = uiList)
            }
        }
    }
}
