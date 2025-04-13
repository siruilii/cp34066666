Login & Translation App — Jetpack Compose + Room + MVVM
This is a feature-complete Android app that combines Jetpack Compose UI, Room local database, MVVM architecture, and NiuTrans translation API. It demonstrates modern Android development practices including user authentication, API integration, and persistent data storage.

Key Features
User Registration & Login (local Room authentication)
Persistent login state across sessions
Chinese ↔ English translation using NiuTrans API
Jetpack Compose UI with reactive state management
Room database to store user hobbies or books
Modular MVVM architecture for clean code separation
Unit test support (e.g., LoginViewModelTest.kt)

Project Structure
├── data/
│   ├── entity/            # Data models (User, Hobby)
│   ├── dao/               # Room DAO interfaces
│   └── repository/        # Data access logic
├── ui/
│   ├── login/             # Login & Register (Jetpack Compose)
│   ├── main/              # Main screen layout
│   └── translation/       # Translation UI
├── viewmodel/             # ViewModel logic
├── network/               # NiuTrans API integration
└── MainActivity.kt        # Navigation control


API Integration — NiuTrans
This app integrates with NiuTrans for online translation. Example usage:
val apiUrl = "http://api.niutrans.com/NiuTransServer/translation"
val client = AsyncHttpClient()

val params = RequestParams().apply {
    put("from", "zh")
    put("to", "en")
    put("apikey", YOUR_API_KEY)
    put("src_text", URLEncoder.encode("Hello", "utf-8"))
}

client.get(apiUrl, params, object : JsonHttpResponseHandler() {
    override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
        val translated = response.getString("tgt_text")
        // Update UI
    }
})

