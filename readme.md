![Наверное, лучшая рекомендация выполненного тестового задания для вакансии с более чем 400 откликами](статистика.PNG)
# Тестовое задание для компании EasyBot
 
Текст тестового находится [здесь](https://www.dropbox.com/s/zpvfea8nz3wqtit/java_test.pdf?dl=0)

Для запуска в контейнере docker использовать 
+ docker run --rm -p 8080:8080 bard1812/easy-bot-test-task:1.0
+ после запуска можно посмотреть документацию по реализованным API по ссылке http://localhost:8080/swagger-ui/index.html#/
+ если 80 порт занят - можно пробросить свободный после флага -р, соответственно ссылка для сваггера поменяет свой порт с 80 на тот, который будет проброшен после флага -р

Можно запустить из IDE, предварительно скопировав репозиторий https://github.com/MarselMingaripov/EasyBotTestTask
+ посмотреть документацию по реализованным API можно по ссылке http://localhost:8080/swagger-ui/index.html#/

Код полностью покрыт тестами
