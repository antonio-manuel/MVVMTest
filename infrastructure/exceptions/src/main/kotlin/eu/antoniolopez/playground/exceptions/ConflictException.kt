package eu.antoniolopez.playground.exceptions

class ConflictException(override val message: String? = null) : NetworkException(message)
