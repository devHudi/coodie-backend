package dev.coodie.api.domain.member.exception

class MemberNotFoundException : IllegalArgumentException(
    "해당 회원을 찾을 수 없습니다."
)

class MemberEmailFormatException : IllegalArgumentException(
    "유효하지 않은 회원 이메일 형태입니다. 올바른 이메일을 전달해주세요."
)

class MemberUsernameFormatException : IllegalArgumentException(
    "유효하지 않은 사용자 이름입니다. 사용자 이름에는 알파벳, 숫자, 언더스코어(_)만이 포함될 수 있습니다."
)

class MemberUsernameLengthException : IllegalArgumentException(
    "유효하지 않은 사용자 이름 길이입니다. 사용자 이름은 1자 이상, 30자 이하여야 합니다."
)

class MemberDisplayNameLengthException : IllegalArgumentException(
    "유효하지 않은 표시 이름 길이입니다. 표시 이름은 1자 이상 20자 이하여야 합니다."
)

class MemberEmailDuplicateException : IllegalArgumentException(
    "이미 가입된 이메일입니다."
)

class MemberUsernameDuplicateException : IllegalArgumentException(
    "이미 존재하는 사용자명입니다."
)
