package io.etip.sdk.hello.greetings

import io.etip.sdk.hello.HelloException

class GreetingFailedException(message: String?) : HelloException(message)
