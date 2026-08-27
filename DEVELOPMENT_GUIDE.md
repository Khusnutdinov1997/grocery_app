# Карта архитектуры GroceryApp (MVVM + Clean Architecture)

Этот гид поможет тебе ориентироваться в проекте и понимать, как добавлять новые фичи по образу и подобию уже существующих.

---

## 1. Слой Domain (Бизнес-логика)
Это "сердце" приложения. Здесь нет кода Android или Firebase, только логика.

*   **Модели данных**: Описывают суть объектов.
    *   *Файлы*: `User.kt`, `OnboardingPage.kt`
    *   *Папка*: `domain/model/`
*   **Интерфейсы репозиториев**: Договор о том, что мы можем делать с данными.
    *   *Файлы*: `AuthRepository.kt`, `OnboardingRepository.kt`
    *   *Папка*: `domain/repository/`

---

## 2. Слой Data (Реализация и Источники)
Здесь мы решаем, откуда берутся данные (Firebase, локальная база, DataStore).

*   **Реализация репозиториев**: Конкретный код работы с сервисами.
    *   *Файлы*: `AuthRepositoryImpl.kt`, `OnboardingRepositoryImpl.kt`
    *   *Папка*: `data/repository/`
*   **Утилиты данных**: Хранилища настроек.
    *   *Файлы*: `DataStoreManager.kt`
    *   *Папка*: `utils/`

---

## 3. Слой Dependency Injection (Связи Hilt)
Этот слой соединяет интерфейсы из Domain с реализациями из Data.

*   **Модули**: Инструкции для Hilt, как создавать объекты.
    *   *Файлы*: 
        *   `RepositoryModule.kt` (связывает интерфейс и реализацию через `@Binds`).
        *   `FirebaseModule.kt` (дает доступ к `FirebaseAuth`).
        *   `DataStoreModule.kt` (дает доступ к `DataStore`).
    *   *Папка*: `di/`

---

## 4. Слой Presentation (Интерфейс пользователя)
Здесь мы готовим данные для Compose и обрабатываем действия юзера.

### Пример фичи "Авторизация" (`presentation/registration/`):
1.  **UI State**: `RegistrationUiState.kt` (хранит email, пароль, ошибки, статус загрузки).
2.  **Events**: `RegistrationEvent.kt` (разовые действия: "уйди на Home", "покажи ошибку").
3.  **ViewModels**: 
    *   `RegistrationViewModel.kt` (логика ввода данных и вызов `signUp/login`).
    *   `AuthViewModel.kt` (проверка сессии при запуске и `logout`).
4.  **UI (Compose)**: 
    *   `RegistrationScreen.kt` (экраны 1, 2, 3 и общие компоненты `CustomInputField`).
    *   `RegistrationScreenMapper.kt` (логика выбора картинок для экранов).

---

## 5. Навигация и Точка входа
То, как все части собираются в одно приложение.

*   **Константы маршрутов**: `Screens.kt` (в папке `utils/`).
*   **Главный диспетчер**: `NavGraph.kt` (папка `presentation/navigate/`). Здесь решается, какой экран показать первым.
*   **MainActivity**: `MainActivity.kt` — входная точка, которая просто запускает `NavGraph` внутри темы.
*   **Application класс**: `GroceryApp.kt` — база для Hilt (помечен `@HiltAndroidApp`).

---

### Как пользоваться этой картой:
Если ты хочешь создать, например, **Список товаров**:
1. Создай `Product.kt` в **Domain**.
2. Создай `ProductRepository` интерфейс в **Domain**.
3. Реализуй его в `ProductRepositoryImpl` в **Data** (используя Firestore).
4. Зарегистрируй его в `RepositoryModule` в **DI**.
5. Создай `ProductViewModel` и `ProductScreen` в **Presentation**.
6. Добавь новый маршрут в `Screens.kt` и `NavGraph.kt`.

**PRO TIP:** Всегда иди сверху вниз по этому списку!
