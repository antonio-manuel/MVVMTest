package eu.antoniolopez.playground.exceptions

class ForbiddenException(override val message: String? = null) : NetworkException(message)
