package com.pg.android_exam.android.View

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("results") val userData: ArrayList<UserData>,
    @SerialName("info") val userInfo: UserInfo
)

//MAIN DATA
@Serializable
data class UserData(
    @SerialName("gender") val gender: String? = "",
    @SerialName("name") val name: Name,
    @SerialName("location") val location: Location,
    @SerialName("email") val email: String? = "",
    @SerialName("login") val login: Login,
    @SerialName("dob") val birth: Birth,
    @SerialName("registered") val register: Register,
    @SerialName("phone") val phone: String? = "",
    @SerialName("cell") val cellnum: String? = "",
    @SerialName("id") val id: ID,
    @SerialName("picture") val picture: Picture,
    @SerialName("nat") val nat: String? = ""
)

@Serializable
data class UserInfo(
    @SerialName("seed")val seed: String? = "",
    @SerialName("results")val results: Int,
    @SerialName("page")val page: Int,
    @SerialName("version")val version: String? = ""
)

//SUB DATAS
@Serializable
data class Name(
    @SerialName("title") val title: String? = "",
    @SerialName("first") val fname: String? = "",
    @SerialName("last") val lname: String? = ""
)

@Serializable
data class Location(
    @SerialName("city") val city: String? = "",
    @SerialName("state") val state: String? = "",
    @SerialName("country") val country: String? = "",
    @SerialName("postcode") val postcode: String? = "",
    @SerialName("street") val street: Street,
    @SerialName("coordinates") val coordinates: Coordinates,
    @SerialName("timezone") val timezone: Timezone
)

@Serializable
data class Street(
    @SerialName("number") val number: String? = "",
    @SerialName("name") val name: String? = ""
)

@Serializable
data class Coordinates(
    @SerialName("latitude") val latitude: String? = "",
    @SerialName("longitude") val longitude: String? = ""
)

@Serializable
data class Timezone(
    @SerialName("offset") val offset: String? = "",
    @SerialName("description") val description: String? = ""
)

@Serializable
data class Login(
    @SerialName("uuid") val uuid: String? = "",
    @SerialName("username") val username: String? = "",
    @SerialName("password") val password: String? = "",
    @SerialName("salt") val salt: String? = "",
    @SerialName("md5") val md5: String? = "",
    @SerialName("sha1") val sha1: String? = "",
    @SerialName("sha256") val sha256: String? = ""
)

@Serializable
data class Birth(
    @SerialName("date") val birthdate: String? = "",
    @SerialName("age") val age: Int
)

@Serializable
data class Register(
    @SerialName("date") val dateReg: String? = "",
    @SerialName("age") val age: Int
)

@Serializable
data class ID(
    @SerialName("name") val name: String? = "",
    @SerialName("value") val value: String? = ""
)

@Serializable
data class Picture(
    @SerialName("large") val large: String? = "",
    @SerialName("medium") val medium: String? = "",
    @SerialName("thumbnail") val thumbnail: String? = ""
)
