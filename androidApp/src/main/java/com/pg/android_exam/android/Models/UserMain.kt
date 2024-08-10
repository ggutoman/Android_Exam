package com.pg.android_exam.android.Models

import kotlinx.serialization.Serializable

//MAIN DATA
@Serializable
data class UserMain(
    val userData: UserData,
    val userInfo: UserInfo
)

@Serializable
data class UserInfo(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)

@Serializable
data class UserData(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val birth: Birth,
    val register: Register,
    val phone: String,
    val cellnum: String,
    val id: ID,
    val picture: Picture,
    val nat: String
)

//SUB DATAS
@Serializable
data class Name(
    val title: String,
    val fname: String,
    val lname: String
)

@Serializable
data class Location(
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val street: Street,
    val coordinates: Coordinates,
    val timezone: Timezone
)

@Serializable
data class Street(
    val number: String,
    val name: String
)

@Serializable
data class Coordinates(
    val latitude: String,
    val longitude: String
)

@Serializable
data class Timezone(
    val offset: String,
    val description: String
)

@Serializable
data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

@Serializable
data class Birth(
    val birthdate: String,
    val age: Int
)

@Serializable
data class Register(
    val dateReg: String,
    val age: Int
)

@Serializable
data class ID(
    val name: String,
    val value: String
)

@Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
