package eu.antoniolopez.playground.exceptions

class UnauthorizedException(override val message: String? = null) : NetworkException(message)
