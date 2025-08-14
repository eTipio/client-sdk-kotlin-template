# Kotlin HTTP API Client Template

A modular, extensible, and reusable SDK template built with **Kotlin** and **Ktor**, designed to simplify the integration with external REST APIs.
This template provides a clean separation between core HTTP functionality (`sdk-core`) and API-specific logic (`sdk-api`), following **SOLID principles**, **semantic versioning**, and best practices for SDK development.

## 📁 Structure Overview

```bash
sdk-template/
├── sdk-core/                      # Core utilities and infrastructure│
├── sdk-api/                       # API-specific integrations
├── examples/                      # Example usage of the SDK
├── .github/workflows/             # CI/CD workflows
└── README.md                      # Project overview and usage guide
```

## 🏗️ SDK Initialization

See example usage in the `examples` directory.

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
