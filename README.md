# Personel Tayin Talebi Uygulaması (Backend-Frontend)

---



## Proje Hakkında


Rol bazlı yetkilendirme bulunmaktadır. Genel Müdür (GNLMDR), Genel Müdür Yardımcısı (GNLMDRYRD), Daire Başkanı(DAIREBSK), Şube Müdürü(SBMDR), Şube Memuru (SBMEMUR) rolleri bulunmaktadır.
Örneğin TALEP TÜRÜNÜ sadece GNLMDR ve GNLMDRYRD rolü belirleyebilecektir. Genel itibariyle onay aşamaları da GNLMDR ve GNLMDRYRD rolündedir.
- Personel kaydı proje kapsamında olmadığından o bölüm yapılmamıştır. Kod içinde static bir şekilde rol/yetki tanımı yapılmıştır.

# 1. AŞAMA = TALEP TÜRLERİNİN belirlenmesi.

Öncelikle yetkili roller tarafından TALEP_TURU belirlenmelidir. Dinamik bir yapıdır.
- ÖRNEKLER  : 
  - ISTEGE_BAGLI_CALISMA_KAPSAMINDA
  - ISTEGE_BAGLI_CALISMA_DISI
  - MAZERET_SAGLIK
  - MAZERET_CAN
  - MAZERET_AILE



Atama ve nakil yönetmeliğinde eklenen veya çıkartılan bir "talep türü" olursa veritabanına bunun eklenmesi yada çıkartılması yeterli olacaktır. Örneğin mevzuata eğitim mazereti eklenirse MAZERET_EGITIM isimli bir talep türü eklenebilir.  Deprem nedeniyle oluşturmak için DEPREM_2025 vs. "Diğer" kaydıda eklenebilir.   İsimlendirme tamamen esnek olup teamüllere göre istenilen şekilde belirlenilebilecektir.
# 2. AŞAMA = TÜM ADLİYE İSİMLERİNİN VERİTABANINA KAYDI
    
    Adliye isimleri veritabanında ayrı bir tabloda tutulmakta olup bir kez adliye isimlerinin veritabanına kaydı gerekmektedir. Verilen dökümanda sadece il isimlerinin yeterli olduğu belirtildiği için 81 il adı ile yetinilmiştir. (TESTADMIN rolünde oturum açıp /test-verilerini-yukle apisine post isteği atılınca Unit test için yazılan adliye isimleri ve test verileri veritabanına kaydedilecektir. Frontend admin panelinde buna ilişkin buton bulunmaktadır. Test verilerini yükle butonuna basılması halinde 81 il, 5 yetkili rol, 50 user, 7 talep türü, 10 ar tane tayin talebi, tayin talebine bağlı 3500 tane tercih edilen adliye)
  


# 3. AŞAMA = TAYİN ÇALIŞMASI başlatılmalıdır.

- 1- Yetkili bir hesap tarafından "TAYİN ÇALIŞMASI" oluşturulmadır. (Yeni Kayıt) Oluştururken kaç talep hakkı olduğu belirtilmedir. Mesela 2025 yılı isteğe bağlı PGM çalışması varsa buna 1 talep hakkı; MAZERET_AILE vs. gibi seçeneklere sınırsız talep hakkı verilmelidir. -1 olarak kaydedilmesi halinde sınırsız talep hakkı tanınır. Personel istediği zaman mazeret, çalışma dışı isteğe bağlı vs. tayin talebinde bulunabileceği için bunun sınırsız olması gerekiyor. Takdir kullanıcının.. Dinamik bir yapı olmasının avantajı.
- 2- Oluşturulan "TAYİN ÇALIŞMASI" GNLMDR veya GNLMDRYRD rolleri tarafından ONAYLANMALI,
- 3- Onaylanan "TAYİN ÇALIŞMASI" yetkili roller tarafından yayına alınmalıdır. Yayına almak; taşra personelinin artık tercihte bulunabilir hale gelmesidir. FrontEnd tarafı istek yapınca tabloya düşecektir. 
 - Üçüncü aşamanın da  tamamlanması halinde taşra personeli (USER) "TAYİN TALEBİ" oluşturubilecektir.


ÖRNEKLER  :  

-  25 SENESİNİ DOLDURANLAR İÇİN İSTEĞE BAĞLI NAKİL ÇALIŞMASI
- XXX SAYILI YASANIN GEÇİCİ 5. MADDESİNE GÖRE NAKİL ÇALIŞMASI
- 2025 YILI KADROLU PERSONELİN İSTEĞE BAĞLI NAKİL ÇALIŞMASI
- Deprem Bölgesi İçin Naklen Atama veya Geçici Görevlendirme Çalışması
- 2025 YILI Sözleşmeli Personelin Karşılıklı Yer Değiştirme Çalışması
- SAĞLIK MAZERETİNE DAYALI TAYİN TALEPLERİ
- AİLE MAZERETİNE DAYALI TAYİN TALEPLERİ
- CAN GÜVENLİĞİ MAZERETİNE DAYALI TAYİN TALEPLERİ

# 4. TAYİN TALEBİNDE BULUNULMASI
User rolü ile kayıtlı olan personel aşağıdaki şekilde talepte bulunabilecektir.

    - Açıklama
    - Tercih edilen adliye
    - Tercih sırası
    

# 5. BAŞVURULARIN GÖRÜNTÜLENMESİ
 Başvuruların görüntülenmesi Admin panelinden yapılabilmektedir. 

    - Burada N+1 sorunu olmaması için ve tüm başvuruların tek seferde çekilememesi için EntityGraph ve Pageable kullanılmıştır.



### Kullanılan Teknolojiler
# Backend
- **Framework**: Spring Boot 3.5.0
- **Dil**: Java 17
- **Veritabanı**: PostgreSQL
- **Güvenlik**: Spring Security, JSON Web Tokens (JWT)
- **Veri Erişimi**: Spring Data JPA (Hibernate)
- **Bağımlılık Yönetimi**: Maven
- **Yardımcı Kütüphaneler**:
  - Lombok (Kod tekrarını azaltmak için)
  - ModelMapper (DTO ve Entity dönüşümleri için)
  - JavaFaker (Test verisi üretimi için)

---

# FrontEnd
-  React-JS-tailwindcss
- **Yardımcı Kütüphaneler**:
    - axios
    - shadcn 
    - vite
    
---
## Özellikler

- **Backend** Katmanlı Mimari kullanılmıştır. Controller, entity, repository, service, dto vb. katmanlar bulunmaktadır.
- **Frontend:** Reactjs kullanılmıştır.

- **Veritabanı:** PostgreSQL kullanılmıştır.
- **Kod Kalitesi:** 
Exceptiona örnek teşkil etmesi için  bir iki husus için özel exception yazıldı. Tüm durumlar için yazılması proje kapsamını aşacağı için yazılmadı. RuntimeException verilmekle yetinildi.
    
    - Kısmi test verilerinin oluşması için basit Unit testi yazıldı.  
     ``` 
       Yazılan test: 
       5 tane talep türü kaydı,
       5 yetkili  kullanıcı(GNLMDR, GNLMDRYRD, SBMEMUR, USER, TESTADMIN) kaydı,
       1.000 USER kaydı,
       1.000 tayin talebi kaydı,
       Taleplere bağlı 10.000 tane tercih kaydı

       
     ```
    -
- **Güvenlik:** 
    - Girdi doğrulaması yapılmaktadir. 
    - Login olunurken girilen parola hashlenerek veritabanında tutulmaktadır.
    - 8080 ve 5173 portu dışından istek kabul etmez.
    

- **JWT Tabanlı Kimlik Doğrulama:** Durumsuz (stateless) oturum yönetimi. Sadece username ile ilişkili yapılmıştır. IP vs. ile yapılabilirdi. Logout olmasından sonra jwt tokenlerinin veritabanında tutulup geçersiz sayılması kısmı  proje kapsamını aşacağından yapılmamıştır. Sadece tarayıcıdan silinmiştir.

- **Rol Bazlı Yetkilendirme (RBAC):** Her API endpoint'i, tanımlanmış kullanıcı rollerine göre erişim kontrolü sağlar.

- **Hiyerarşik Onay Mekanizması:** Taleplerin belirli rollerdeki yöneticiler (örneğin Genel Müdür, Genel Müdür Yardımcısı) tarafından onaylanması.
- **REST API :**  Spring Web (RestController)

- **Loglama:** logs klasörü altında log kaydı tutulmaktadır. properties dosyasından hangi logların alınacağı belirlenebilir. Mevcutta 5 MB büyüklüğünde 5 dosya olarak çalışmaktadır.

- **YÖNETİM PANELİ** Kısıtlı olarak admin paneli oluşturulmuştur. 
Rol bazlı yetkilendirme vardır. GNL,GNLMDRYRD,DAIREBSK,SBMDR,SBMEMUR rolleri, rollerine uygun şekilde bir kısım işlemleri yapabilir.

  - Tüm yayında olan çalışmaları listeler- HEPSİ
  - Tüm çalışmaları onaylar- GNL,GNLMDR
  - tüm çalışmaları yayına alır-SBMEMUR harici HEPSİ
  - Çalışmaya dair tüm başvura taleplerini listeler-HEPSİ

---


### Gereksinimler

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/) veritabanı
- Node.js


### Kurulum

Özet anlatım.

ADIMLARI TAKİP EDİNİZ. DOSYA YOLLARI VE ŞİFREYİ KENDİNİZE GÖRE DÜZENLEYİN.

Postgresql de erd_db adında bir database oluşturun. Pg4Admin4 üzerinden ya da terminalden postgresql oturumu açıp Şu komutla:

```
CREATE DATABASE erd_db;
```

Database oluştuktan sonra tablolar ve şemalar yazılım tarafından otomatik oluşturalacaktır.

ÖNCELİKLE Jar dosyasını oluşturmak için Dosya yollarını kendinize göre değiştirin.

Aşağıdaki komutu çalıştırın. pom.xml in buluduğu klasöre cd ile girin.

```bash
$env:JAVA_HOME = "C:\JAVALAR\jdk-17.0.0.1" 
$env:PATH = "$env:JAVA_HOME\bin;" + $env:PATH
mvn clean install
```

Veritabanına test verilerinin ve tablolarının oluşması için aşağıdaki dev modunda çalıştırmanız gerekiyor.

```bash
$env:JAVA_HOME = "C:\JAVALAR\jdk-17.0.0.1" 
$env:PATH = "$env:JAVA_HOME\bin;" + $env:PATH
```
Postgresql şifrenizi ve kullanıcı adınıza göre revize edin.
```bash
$env:DB_USERNAME = "postgres" 
$env:DB_PASSWORD = "enes"
```

```bash
java -jar target/personel-tayin-talebi-0.0.1.jar --spring.profiles.active=dev
```

Test verilerini oluşturmak için aşağıdaki link tarayıcıdan çalıştırın. http://localhost:8080/test-verilerini-yukle

çalıştırın..

Terminalde Ctrl C tuşuna basarak spring bootu durdurun.

Aynı ekranda bu sefer aşağıdaki komutu çalıştırın.

```bash
java -jar target/personel-tayin-talebi-0.0.1.jar
```

ARtık kullanıma hazır.. Aşağıdaki linkten girebilirsiniz..

http://localhost:8080/

 PERSONEL KAYDI OLUŞTURUR
        // GNLMDR, GNLMDRYRD, SBMEMUR, USER, TESTADMIN, SBMDR
        // 11111, 22222, 33333, 44444, 55555, 66666 sicilleri sırasıyla.
        // PASSWORD 1234 HEPSİNİN

1.  **Projeyi Klonlayın**
    Proyeyi klonlayın. Öncelikle yukarıdaki özet anlatımı okuyunuz.
    

2.  **Veritabanını Ayarlayın**
    - PostgreSQL'de `erd_db` adında bir veritabanı oluşturun.

3.  **Application Properties'i Yapılandırın**
    - `src/main/resources/` dizininde `application.properties` dosyasını açın ve aşağıdaki username ve password  bilgileri yazınız.

    ```properties
    # PostgreSQL Veritabanı Ayarları
    spring.datasource.url=jdbc:postgresql://localhost:5432/erd_db
    spring.datasource.username=postgres
    spring.datasource.password=sifreniz
    spring.jpa.hibernate.ddl-auto=create
    

    ```

4.  **Maven Bağımlılıklarını Yükleyin**
    ```sh
    mvn clean install ile Backend kısmını bağımlılıkların indirin
	npm install ile Frontend kısmını bağımlılıklarını indirin
    ```

5.  **Uygulamayı Çalıştırın**
     
     Öncelikle jdk 17 yi yukarıdaki linkten indirin.
Windows PowerShell komut satırında jdk yolunu kendinize göre düzeltip aşağıdaki komutu çalıştırın. Bu o terminalda geçici olarak jdk17 çalışmasını sağlar. Terminal kapanınca tekrar sistem ayarları aktif olur.

        $env:JAVA_HOME = "C:\JAVALAR\jdk-17.0.0.1"
        $env:PATH = "$env:JAVA_HOME\bin;" + $env:PATH


     Sonra React kısmını npm run build komutu ile derleyin. build klasörü oluşacaktır.
     Build dosyasını backende resource/static klasörü içine kopyalayın.
     Backende mvn ile mvn install komutu ile jar dosyasını oluşturun.

    Uygulama varsayılan olarak `http://localhost:8080` portunda çalışmaya başlayacaktır. React ile yazılan frontend build edilip resource klasöründe static içine konulması halinde aynı porttan ikisi de çalışacaktır. Backend üzerinden çalıştıracaksınız, api.js de base_url linki "/" ile değiştirin. İlgili dosyada açıklama mevcuttur.

    Reactı ayrı çalıştıracaksınız, npm run dev komutu ile 5173 portundan  çalıştırınız. Backend 5173 ve kendi portu olan 8080 'e izin vermektedir. 

---

## API Endpoint'leri ve Yetkilendirme

Uygulama, `SecurityFilterChain` ile tanımlanmış rol bazlı bir yetkilendirme şeması kullanır.

### Kullanıcı Rolleri

- `TESTADMIN`: Tüm yetkilere sahip test ve geliştirici rolü.
- `GNLMDR`: Genel Müdür
- `GNLMDRYRD`: Genel Müdür Yardımcısı
- `DAIREBSK`: Daire Başkanı
- `SBMDR`: Şube Müdürü
- `SBMEMUR`: Şube Memuru

