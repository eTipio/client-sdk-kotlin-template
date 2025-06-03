# Kotlin HTTP API Client Template

A modular, extensible, and reusable SDK template built with **Kotlin** and **Ktor**, designed to simplify the integration with external REST APIs. This template provides a clean separation between core HTTP functionality (`sdk-core`) and API-specific logic (`sdk-api`), following **SOLID principles**, **semantic versioning**, and best practices for SDK development.

## ✨ Features

- 🔌 **Modular architecture** (`sdk-core`, `sdk-api`)
- 🔄 Generic HTTP client with support for **GET**, **POST**, **PUT**, and **DELETE**
- 🔐 Pluggable authentication (JWT, Basic Auth, or custom)
- ✅ Built-in support for **error handling** and **response validation**
- 📦 Easily publishable to GitHub Packages
- 🚀 Ready-to-use SDK factory for simplified instantiation
- 📘 Example integration using [PokéAPI](https://pokeapi.co)

## 📁 Structure Overview

```bash
sdk-template/
├── sdk-core/                      # Core utilities and infrastructure
│   └── src/main/kotlin/io/etip/sdk/core/
│       ├── ApiConfig.kt            # Config necessary to be used by HttpClientFactory like logging enabler and timeouts
│       ├── BaseHttpClient.kt       # Generic HTTP client with GET/POST support
│       ├── HttpClientFactory.kt    # Factory responsible for creating and configuring instances of `HttpClient`.
│       ├── exceptions/             # Common SDK exceptions
│       └── routes/                 # Sealed route definitions
│
├── sdk-api/                       # API-specific integrations
│   └── src/main/kotlin/io/etip/sdk/api/
│       ├── config/                 # Contains config manager, provider, keys, factory, and properties API configurations
│       ├── config/mock/            # MockConfigProvider - Can be used if there is no config provider configured
│       ├── di/                     # ApiRegistry, AppModule
│       ├── integration/            # ApiService interface and implementation
│
├── examples/                      # Example usage of the SDK
│   └── basic-example/             # Pokémon API usage demo
│
├── .github/workflows/             # CI/CD workflows for release
│   └── release.yml
│
└── README.md                      # Project overview and usage guide
```

## 🏗️ SDK Initialization

The SDK supports secure and environment-aware initialization using a factory method. Here’s how you initialize it in your application:

```kotlin
fun main() = runBlocking {
    val logger = LoggerFactory.getLogger("Pokemon")
    val dotenv = Dotenv.configure().ignoreIfMissing().load()

    val pokemon = Pokemon.fromEnv()

    val context = pokemon.getContext()
    val secrets = pokemon.getSecrets()
    val configs = pokemon.getConfigs()

    val runMode = context["runMode"]

    logger.info("Run mode: $runMode")

    logger.info("------------------------CONFIG------------------------")
    logger.info(configs.toString())
    logger.info("------------------------SECRET------------------------")
    logger.info(secrets.toString())
    logger.info("------------------------CONTEXT------------------------")
    logger.info(context.toString())
    logger.info("------------------------ENV----------------------------")
    logger.info(dotenv.entries().toString())
    AppModule.init(Environment.STAGE)

    val pokemonDetail = ApiRegistry.getApi<PokemonApiService>().getPokemonByName("pikachu")
    println(pokemonDetail)
}
```

### 🔐 Authentication Strategy

#### In sdk-core

The SDK uses Ktor’s flexible plugin system to support authentication through header injection. Authentication is not hard-coded in `sdk-core`-instead, it's configured through `ApiConfig`, which passes values like:

- username
- password
- baseUrl
- enableLogging
- timeoutMillis

#### In `sdk-api`

Each integration provides a `ClientConfig` used to build a proper `ApiConfig`. The SDK is initialized using a configuration factory pattern (like `Pokemon.fromEnv()`), which retrieves configs and secrets dynamically and bootstraps everything needed.

#### 🛠 Example of HttpClientFactory Implementation

```kotlin
object HttpClientFactory {
    fun create(config: ApiConfig): HttpClient {
        return HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = config.timeoutMillis
                connectTimeoutMillis = config.timeoutMillis
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            install(Auth) {
                basic {
                    credentials {
                        BasicAuthCredentials(username = config.username, password = config.password)
                    }
                    sendWithoutRequest { true }
                }
            }

            if (config.enableLogging) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }

            defaultRequest {
                url(config.baseUrl)
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    throw when (cause) {
                        is ClientRequestException -> SdkException("Client error", cause)
                        is ServerResponseException -> SdkException("Server error", cause)
                        else -> SdkException("Unexpected error", cause)
                    }
                }
            }
        }
    }
}
```

#### 🔧 Configuration Source (PokemonConfiguration)

The SDK can be configured using:

- `.env` files (via [dotenv-kotlin](https://github.com/cdimascio/dotenv-kotlin))
- Secrets management (e.g., AWS Secrets Manager or Vault)
- Runtime context (e.g., environment variables)

Example:

```kotlin
val pokemon = Pokemon.fromEnv()
val configs = pokemon.getConfigs()
val secrets = pokemon.getSecrets()
```

License
--------

    Copyright 2025 eTip, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
