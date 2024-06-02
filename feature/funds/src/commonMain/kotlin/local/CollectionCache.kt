package org.pointyware.painteddogs.feature.collections.core.local

import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid

interface CollectionCache {
    fun save(it: Fund)
    fun findById(id: Uuid): Fund?
    suspend fun search(query: String): Result<List<Fund>>
}

class InMemoryCollectionCache : CollectionCache {

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
}
