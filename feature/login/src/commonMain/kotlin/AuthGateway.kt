package org.pointyware.painteddogs.feature.login

/**
 * Provides a gateway to create a new user or login existing users.
 */
interface AuthGateway {
    /**
     * Creates a new user with the given email and password.
     *
     * @param email The email of the new user.
     * @param password The password of the new user.
     * @return The user id of the new user.
     */
    suspend fun createUser(email: String, password: String): Result<AuthenticatedUser>

    /**
     * Logs in an existing user with the given email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The user id of the user.
     */
    suspend fun login(email: String, password: String): Result<AuthenticatedUser>
}
