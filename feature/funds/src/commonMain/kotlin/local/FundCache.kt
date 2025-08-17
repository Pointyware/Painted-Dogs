package org.pointyware.painteddogs.feature.funds.local

import org.pointyware.painteddogs.core.entities.Fund
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface FundCache {
    fun save(it: Fund)
    fun findById(id: Uuid): Fund?
    suspend fun search(query: String): Result<List<Fund>>
    suspend fun delete(fund: Fund): Result<Unit>
}

@OptIn(ExperimentalUuidApi::class)
class InMemoryFundCache : FundCache {

    /**
     * Mutable map of collections
     */
    private var collections: MutableMap<Uuid, Fund> = mutableMapOf()
    override fun save(it: Fund) {
        collections[it.id] = it
    }

    override fun findById(id: Uuid): Fund? {
        return collections[id]
    }

    override suspend fun search(query: String): Result<List<Fund>> {
        return collections.filter { it.value.title.contains(query, ignoreCase = true) }.values.toList().let {
            Result.success(it)
        }
    }

    override suspend fun delete(fund: Fund): Result<Unit> {
        return collections.remove(fund.id)?.let { Result.success(Unit) } ?: Result.failure(Exception("Fund not found"))
    }
}
