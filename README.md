# Hangman-Game-V1 Api

Proje adam asmaca oyununun version 1 api'sidir. Projeyi build ettikten sonra tarayiciniz ile 'http://localhost:8080/swagger-ui.html' adresine gidip api'ye ait tüm servisleri görebilir ve bu servislere request atıp, response'larini alabilirsiniz. 

# Servisler

* http://localhost:8080/hangman/v1/game (GET) -> Bu API daha önce olusturulmus bütün oyunları listeler.

* http://localhost:8080/hangman/v1/game (POST) -> Oyuncu için yeni oyun başlatır. Player nesnesi bu request için body 'de gönderilir.

* http://localhost:8080/hangman/v1/game/{gameId} (DELETE) -> Bu API kullanıcının oyunu bırakması sağlar. Oyun bırakıldıktan sonra bütün kelime kullanıcıya görüntülenir. Oyun kaybedilmiş olarak işaretlenir.

* http://localhost:8080/hangman/v1/game/{gameId} (GET) -> Bu API size tek bir oyunun detaylarını getirir.

* http://localhost:8080/hangman/v1/game/{gameId} (PUT) -> Bu API bir oyun için harf tahmini yapmanızı sağlar. Eğer yanlış tahmin ederseniz ve tahmin hakkınız yoksa oyun biter. Eğer harfi doğru tahmin ederseniz oyun devam eder. Bütün harfi tahmin etmek bu versionda olmayacaktır.

* http://localhost:8080/hangman/v1/player (GET) -> Bu API bütün olusturulmus kullanıcıları listeler.

* http://localhost:8080/hangman/v1/player (PORT) -> Bu API yeni kullanıcı olusturur. Eğer oyuncu adı daha önce alınmışsa 400 hatası fırlatılır.

* http://localhost:8080/hangman/v1/player/{playerId} -> Bu API oyuncu bilgisi detayını getirir.

# Projenin Kullanılan Teknolojiler 

- Projemiz Spring Boot Mvc projesidir.
- Loglama için Aop kullanilmistir.
- Rest Api'mizi dökümante edebilmek amaci ile swagger kullanilmistir.
- ORM aracı olarak Spring Data Jpa kullanilmistir.
- Db icin Postgres SQL kullanilmisitir.
- Datalarimizi mock'lamak icin Mockito kullanilmistir.
- Projenin build edilmesi icin Docker kullanilmistir.
- Junit
- Lombok
- Maven

# Projenin Build Edilmesi

Projenin İntellij idea araciliyla build islemi:

  Projeyi Github'dan bilgisayarimizi indirdikten sonra İntellij idea acilir ve File->New->Module From Existing Source tıklanir ve projenin   oldugu dizin gosterilir. Maven secilir ve next'e basilarak proje eklenmis olur. Edit configuration acilir ve Spring Boot secilir main     class olarak HangmanGameApplication.java class' inin oldugu path verilir. JRE olarakta 1.8 secilir ve run edilir.
  
Projenin Docker araciliyla build islemi:  

  Asagidaki iki komut calistirilir ve projemizin build olaracaktir.
  - docker build -f Dockerfile -t hangman-game .
  - docker run -p 8080:8080 hangman-game
  
  NOT: Eger local ortamimizda calisan bir database'e baglanicaksak Docker conteiner'in icinden makinamizin localhost'una baglanmak icin     bir kac ayar yapmamiz gerekmektedir. Aksi halde localhost'unuzda olan database'inize connection kuramayacaksiniz ve proje build           olmayacaktir.



