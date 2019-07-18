package eu.antoniolopez.playground.exceptions

class BadRequestException(override val message: String? = null) : NetworkException(message)
