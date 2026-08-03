Для добавления Firebase в проект:
    1. Зарегистрировать наше приложение на firebase.
    2. Получить google-services.json файл после регистрации и добавить его в проект на уровне /app
    3. Добавить зависимости для firebase:

        firebase-bom = { group = "com.google.firebase", name = "firebase-bom", version.ref = "firebaseBom" }
        firebase-auth = { group = "com.google.firebase", name = "firebase-auth" } 
            //plagins app
        id("com.google.gms.google-services")
            //dependency
        implementation(platform(libs.firebase.bom)) 
        implementation(libs.firebase.auth)
            // plagins project
        id("com.google.gms.google-services") version "4.4.2" apply false

