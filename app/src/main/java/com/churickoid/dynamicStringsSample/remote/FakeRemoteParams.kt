package com.churickoid.dynamicStringsSample.remote

import javax.inject.Singleton

object FakeRemoteParams {

    fun getDynamicResourcesJson(): String {
        return testJson
    }

    fun isDynamicStringEnabled(): Boolean {
        return true
    }

        private val testJson = """ [
  {
    "type": "string",
    "enabled": true,
    "name": "test_string",
    "replacements": [
      {
        "locale": "en",
        "value": "override string"
      },
      {
        "locale": "ru",
        "value": "подменненая строка"
      }
    ]
  },
  {
    "type": "string",
    "enabled": true,
    "name": "string_resource_name",
    "replacements": [
      {
        "locale": "en",
        "value": "resource_value"
      },
      {
        "locale": "ru",
        "value": "resource_value"
      }
    ]
  },
  {
    "type": "plurals",
    "name": "plurals_resource_name",
    "enabled": true,
    "replacements": [
      {
        "locale": "en",
        "items": [
          {
            "quantity": "zero",
            "value": "resource_value"
          },
          {
            "quantity": "one",
            "value": "resource_value"
          }
        ]
      },
      {
        "locale": "ru",
        "items": [
          {
            "quantity": "zero",
            "value": "resource_value"
          },
          {
            "quantity": "one",
            "value": "resource_value"
          }
        ]
      }
    ]
  }
]
"""
    }
