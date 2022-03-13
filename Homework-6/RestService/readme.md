# REST VE RESTFUL WEB SERVİS NEDİR?
Merhaba, REST ve RESTful Web Servis kavramlarından ve HTTP metotlarından bahsedeceğim.
REST(Representational State Transfer), 2000 yılında Roy Fielding tarafından doktora tezinde tanıtılmış ve tanımlanmıştır.REST, dağıtık sistemler tasarlamak için kullanılan bir mimari tarzdır.
REST, client-server arasındaki haberleşmeyi sağlayan HTTP protokolü üzerinden çalışan bir mimaridir. REST ,servis yönelimli mimari üzerine oluşturulan yazılımlarda kullanılan bir transfer yöntemidir.İstemci ve sunucu arasında XML ve JSON verilerini taşıyarak uygulamanın haberleşmesini sağlar.REST mimarisini kullanan servislere ise RESTful servis denir.

## HTTP Metotları
### GET
Veri listeleme ,veri görüntülemek için kullanılır.GET request’ler güvenli ve idempotent olmalıdır, yani aynı parametrelerle kaç kez tekrar ettiğine bakılmaksızın sonuçlar aynıdır.GET ile veri gönderilirken ,adres çubuğunda gönderilir.Gönderilen değişkenler ve veriler adres çubuğunda görüntülendiği için güvenilir değildir.Ancak veriler MD5 şifreleme ile güvenilir bir şekilde de gönderilebilir.
Örneğin: Get ile sipariş detaylarını getirebiliriz.
GET : /orders/{id}

### POST
Veri eklemek için kullanılır ancak mevcut olan bir veriyi güncellemek için de kullanılır.Doğrudan sayfaya veri gönderilir ve veriler adres çubuğunda görünmez .Daha güvenilir yöntemdir.
Örneğin: POST ile yeni bir sipariş oluştururuz.
POST: /orders/
Aşağıdaki şekilde GET ve POST farklılıkları daha rahat bir şekilde görülmektedir.

## İLİŞKİLİ APİ UÇLARI
### PUT
Veri güncellemek için kullanılır.PUT ‘un POST’dan farkı idempotent olmasıdır.Yani bir request birden fazla kez tekrarlansa da sonucunun aynı olmasıdır.
Örneğin: PUT ile belirli sipariş güncelleriz.
PUT : /orders/{id}
Örneğin :
PUT /orders de update edilecek bir sipariş yok.Tüm siparişleri de güncelleyemeyeceğimize göre PUT metodunu kullanamayız.
### PATCH
Verinin sadece bir parçasını güncellemek için kullanılır.
PATCH /addresses/1
### DELETE
Kaynaktan veriyi silmek için kullanılır.
Örneğin: Id’si verilen adresi sileriz.
DELETE /addresses/id
### HTTP status codes
- 1XX ile başlayan kodlar bilgi amaçlı kodlardır.
- 2XX ile başlayan kodlar başarı kodlarıdır.
- 3XX ile başlayan kodlar yönlendirme kodlarıdır.
- 4XX ile başlayan kodlar ise client(istemci) hatası kodlarıdır.
- 5XX ile başlayan kodlar ise server(sunucu) hatası kodlarıdır.

Kaynak: https://medium.com/@bsrutmn/rest-ve-restful-web-servi̇s-nedi̇r-7258b7db7f66