# Сервис «Портфель клиента» (Portfolio Service)

Это REST-сервис для управления данными клиентов и их счетами. Является частью кейса по разработке API-адаптера.

## 📌 Требования к системе
- **Java 17** (или выше).
- **PostgreSQL** (рекомендуется версия 14+), запущенный локально на порту `5432`.

---

## 1. Подготовка базы данных

Перед первым запуском необходимо создать базу данных в PostgreSQL.

Выполните в любом SQL-клиенте (pgAdmin, DBeaver, DataGrip или через консоль `psql`):

```sql
CREATE DATABASE portfolio_db;

Параметры подключения по умолчанию:
Пользователь: postgres
Пароль: postgres
Порт: 5432
```

## 2. Запуск сервиса
   В терминале перейдите в папку с JAR-файлом(target) и выполните команду:

```bash
java -jar portfolio-service.jar
```

После запуска сервис будет доступен по адресу:
👉 http://localhost:8081
В консоли вы должны увидеть лог: Started PortfolioApplication in ... seconds.


## 3. Эндпоинты REST API
   Все эндпоинты находятся в корне: /api/v1/clients

3.1. Работа с клиентами (таблица clients)
Метод	URL	Описание
POST	/api/v1/clients	Создать нового клиента
GET	/api/v1/clients	Получить список всех клиентов
GET	/api/v1/clients/{id}	Получить клиента по ID
PATCH	/api/v1/clients/{id}/aml-status	Обновить AML-статус клиента (принимает { "amlStatus": true/false })

Пример запроса на создание клиента (JSON):

```json
POST http://localhost:8081/api/v1/clients
Content-Type: application/json

{
"fio": "Иванов Иван Иванович",
"region": "Москва",
"phone": "+7-999-111-22-33",
"inn": "123456789012",
"snils": "123-456-789 00"
}
```

## 3.2. Работа со счетами (таблица accounts)
Метод	URL	Описание
POST	/api/v1/clients/accounts	Создать новый счёт
GET	/api/v1/clients/accounts	Получить список всех счетов
GET	/api/v1/clients/accounts/{id}	Получить счёт по ID
Пример запроса на создание счета (JSON):

```json
POST http://localhost:8081/api/v1/clients/accounts
Content-Type: application/json

{
"fio": "Иванов Иван Иванович",
"accountNumber": "4081781012345",
"balance": 1000.00
}
```
