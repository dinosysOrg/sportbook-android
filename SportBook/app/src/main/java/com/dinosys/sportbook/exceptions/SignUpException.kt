package com.dinosys.sportbook.exceptions


class SignUpEmailNullOrEmptyException(message: String?): Exception(message)

class SignUpEmailInvalidException(message: String?): Exception(message)

class SignUpPasswordNullOrEmptyException(message: String?): Exception(message)

class SignUpPasswordNotMatchException(message: String?): Exception(message)

class SignUpPasswordInvalidException(message: String?): Exception(message)

class SignUpPasswordConfirmInvalidException(message: String?): Exception(message)

class SignUpPasswordConfirmINullOrEmptyException(message: String?): Exception(message)

class SignUpWithFailureException(message: String?): Exception(message)
