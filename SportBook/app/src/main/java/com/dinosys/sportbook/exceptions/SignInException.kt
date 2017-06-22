package com.dinosys.sportbook.exceptions


class SignInEmailNullOrEmptyException(message: String?): Exception(message)

class SignInEmailInvalidException(message: String?): Exception(message)

class SignInPasswordNullOrEmptyException(message: String?): Exception(message)

class SignInPasswordInvalidException(message: String?): Exception(message)

class SignInWithFailureException(message: String?): Exception(message)
