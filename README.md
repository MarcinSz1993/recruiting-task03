# 📌 Zadanie rekrutacyjne

Aplikacja wykorzystuje API GitHuba do pobierania danych niezbędnych do wykonania zadania, a następnie przetwarza je zgodnie z wytycznymi.

Na podstawie **nicku użytkownika** serwisu GitHub pobierane są jego **repozytoria**, filtrowane pod kątem tego, czy są forkami, a następnie pobierane są gałęzie tych repozytoriów wraz z **ostatnim SHA commitu**. Dane są przetwarzane i wyświetlane w określonym formacie.

W przypadku nieznalezienia użytkownika na GitHubie rzucany jest wyjątek w wymaganym formacie.

---

## ⚙️ Technologie wykorzystane w projekcie

- **Java 21**
- **Spring Boot 3.4.3**
- **Maven**
- **Lombok**
- **Spring Web**
- **AssertJ**

---

## 🚀 Uruchomienie aplikacji

Aby uruchomić aplikację lokalnie za pomocą **Maven**, wykonaj:

```sh
mvn clean install
mvn spring-boot:run
```
Aplikacja powinna być dostępna pod adresem:
👉 http://localhost:8080

🌍 Endpoint wykonujący polecenie w zadaniu

    GET /api/solution
    Przyjmuje parametr:
        username (String) – nazwa użytkownika GitHuba

🔹 Przykładowe użycie:

http://localhost:8080/api/solution?username=MarcinSz1993

🔹 Przykładowa odpowiedź:
```json
[
  {
    "repositoryName": "bankservice",
    "ownerLogin": "MarcinSz1993",
    "branchList": [
      {
        "name": "master",
        "commit": {
          "sha": "2c575921c91749f5664c41c64055a452ea8985f8"
        }
      }
    ]
  }
]
```


❌ Obsługa błędu (użytkownik nie istnieje):
```json
{
  "statusCode": 404,
  "message": "A user with username xxxxxxxxxyyyyyyyyyzzzzzzzz does not exist on GitHub."
}
```

👨‍💻 Autor

    Marcin Szabała
    📧 Kontakt: marcinsz1993@hotmail.com
