# Kotlin HTTP API Client Template

A modular, extensible, and reusable SDK template built with **Kotlin** and **Ktor**, designed to simplify the integration with external REST APIs. This template provides a clean separation between core HTTP functionality (`sdk-core`) and API-specific logic (`sdk-api`), following **SOLID principles**, **semantic versioning**, and best practices for SDK development.

## ✨ Features

- 🔌 **Modular architecture** (`sdk-core`, `sdk-api`)
- 🔄 Generic HTTP client with support for **GET** and **POST**
- 🔐 Pluggable authentication (JWT, Basic Auth, or custom)
- ✅ Built-in support for **error handling** and **response validation**
- 📦 Easily publishable to GitHub Packages
- 🚀 Ready-to-use SDK factory for simplified instantiation
- 📘 Example integration using [PokéAPI](https://pokeapi.co)

## 📁 Structure Overview

```plaintext
sdk-template/
├── sdk-core/                      # Core utilities and infrastructure
│   └── src/main/kotlin/io/etip/sdk/core/
│       ├── BaseHttpClient.kt       # Generic HTTP client with GET/POST support
│       ├── HttpClientFactory.kt    # Factory responsible for creating and configuring instances of `HttpClient`.
│       ├── exceptions/             # Common SDK exceptions
│       └── routes/                 # Sealed route definitions
│
├── sdk-api/                       # API-specific integrations
│   └── src/main/kotlin/io/etip/sdk/api/
│       ├── config/                 # ClientConfig, Environment enum
│       ├── integration/            # ApiService interface and implementation
│       └── factory/                # SdkFactory to instantiate the API client
│
├── examples/                      # Example usage of the SDK
│   └── basic-example/             # Pokémon API usage demo
│
├── .github/workflows/             # CI/CD workflows for release
│   └── release.yml
│
└── README.md                      # Project overview and usage guide
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
