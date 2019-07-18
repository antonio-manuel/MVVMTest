package eu.antoniolopez.playground.exceptions

class NotFoundException(override val message: String? = null) : Throwable(message)
