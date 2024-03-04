package org.pointyware.painteddogs.feature.collections.core.local

import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection

interface CollectionCache {
    fun save(it: Collection)
    fun findById(id: Uuid): Collection?
}

class InMemoryCollectionCache : CollectionCache {

    /**
     * Mutable map of collections
     */
    private var collections: MutableMap<Uuid, Collection> = mutableMapOf()
    override fun save(it: Collection) {
        collections[it.id] = it
    }

    override fun findById(id: Uuid): Collection? {
        return collections[id]
    }
}
