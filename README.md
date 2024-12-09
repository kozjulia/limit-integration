##  Сервис интеграции сервиса лимитов, платежного сервиса и сервиса продуктов


_______

Приложение **умеет** делать следующее:
- Для каждого юзера в БД хранится дневной лимит возможных платежей (первоначально 10000.00. Считаем, что раз в несколько месяцев он может меняться);
- В 00.00 каждого дня лимит для всех пользователей должен быть сброшен;
- Про успешном проведении платежа лимит должен быть уменьшен на соответствующую сумму;
- Если вдруг платеж по какой-то причине не прошел, то восстанавливается списанный лимит;
- В БД храним лимиты для юзеров, которые были созданы в сервисе юзеров;
- При запросе лимита с ID, который отсутствует в БД, создаем новую запись под него со стандартным значением лимита;
- Запрашивать продукты у платежного сервиса (клиент кидает запрос в платежный сервис, платежный сервис запрашивает продукты клиента у сервиса продуктов и возвращает клиенту результат);
- В процессе исполнения платежа доступен выбор продукта, проверку его существования и достаточности средств на нем;
- Возвращать ошибки клиенту о проблемах как на стороне платежного сервиса, так и на стороне сервиса продуктов.

-------

Приложение написано на **Java**, использует **Spring Boot**, **Maven**, **Flyway**, **OpenFeign**, **JPA**, **Swagger**, **Mapstruct**
данные хранятся в БД **PostgreSQL**.

## Swagger

Платежный сервис:
http://localhost:8080/swagger-ui/index.html

Сервис продуктов:
http://localhost:8081/swagger-ui/index.html

Сервис лимитов:
http://localhost:8082/swagger-ui/index.html
