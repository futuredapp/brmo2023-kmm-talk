package app.futured.brmo23

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform