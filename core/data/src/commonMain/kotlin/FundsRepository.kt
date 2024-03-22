import org.pointyware.painteddogs.core.entities.Fund

/**
 * Defines the interface for a repository that manages funds.
 */
interface FundsRepository {
    suspend fun getFund(collectionId: String): Result<Fund>
    suspend fun getFunds(): Result<List<Fund>>
    suspend fun addFund(collection: Fund)
    suspend fun updateFund(collection: Fund): Result<Fund>
    suspend fun deleteFund(collectionId: String)
}
