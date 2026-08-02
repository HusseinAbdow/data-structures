# Ödev 1 - Bağlı Liste Ekleme Metodu

Hazırlayanlar: Ekip çalışması

## Soru

25 adet rastgele üretilen sayıyı bir bağlı listeye ekleyiniz.

Bağlı listeye sayı ekleme işlemi her adımda küçükten büyüğe sıralı yapılmalıdır.

Örneğin sırasıyla `55`, `44`, `33`, `40` ve `45` sayılarının ekleneceğini varsayalım:

```text
1. adım 55
2. adım 44 55
3. adım 33 44 55
4. adım 33 40 44 55
5. adım 33 40 44 45 55
```

## Çözüm

Bu projede Java ile çift yönlü bağlı liste kullanıldı. Program önce 25 adet rastgele sayı üretir. Daha sonra her sayıyı bağlı listeye eklerken listenin sıralı kalacağı konumu bulur.

Her ekleme işleminden sonra bağlı listenin güncel hali `1. adım`, `2. adım`, `3. adım` şeklinde ekrana yazdırılır.

## Çalıştırma

```bash
javac -encoding UTF-8 -d bin src/module-info.java src/veriOdev1/*.java
java -cp bin veriOdev1.Main
```

## Örnek Çıktı

Rastgele sayılar her çalıştırmada değişebilir. Programın ekleme kısmı aşağıdaki gibi görünür:

```text
Sıralanmamış 25 adet rastgele sayı:
[55, 44, 33, 40, 45, ...]

1. adım 55
2. adım 44 55
3. adım 33 44 55
4. adım 33 40 44 55
5. adım 33 40 44 45 55
```

## Nasıl Çalışır?

Programın ana akışı `Main.java` dosyasındadır.

**Ana Akış**

`main` metodunda:

1. `Random` ile rastgele sayılar üretilir.
2. `HashSet` tekrar eden sayıları engellemek için kullanılır.
3. 25 sayı üretildikten sonra her sayı `insert` metoduna gönderilir.
4. `insert` metodu sayının bağlı listede nereye ekleneceğini bulur.
5. Her ekleme işleminden sonra `printAdim` metodu listenin güncel halini yazdırır.
6. Son olarak kullanıcıdan bir sayı seçmesi istenir ve seçilen sayı yeni konuma taşınabilir.

**Sıralı Ekleme**

`insert` metodunda dört durum kontrol edilir:

- Liste boşsa yeni sayı ilk düğüm olur.
- Yeni sayı listedeki ilk sayıdan küçükse başa eklenir.
- Yeni sayı listedeki bütün sayılardan büyükse sona eklenir.
- Yeni sayı iki sayı arasında kalıyorsa o iki düğümün arasına eklenir.

Bu sayede liste her adımdan sonra küçükten büyüğe sıralı kalır.

**Adım Adım Yazdırma**

Her eklemeden sonra `printAdim` metodu çalışır:

```text
adım numarası + güncel bağlı liste
```

Örneğin:

```text
4. adım 33 40 44 55
```

Bu çıktı, dördüncü sayı eklendikten sonra listenin güncel sıralı halini gösterir.

**Kes/Yapıştır Bölümü**

25 sayı eklendikten sonra kullanıcı listeden bir sayı seçebilir. Seçilen sayı önce mevcut konumundan silinir, sonra kullanıcının seçimine göre:

- listenin başına taşınır,
- listenin sonuna taşınır,
- iki sayı arasına taşınır.

Bu işlemler için `insertBas`, `insertSon`, `insertOrta` ve `delete` metotları kullanılır.

## Kontrol Etme

Programı hızlıca kontrol etmek için bulunmayan bir sayı girilebilir. Böylece program 25 adımlık ekleme çıktısını gösterir ve taşıma menüsüne girmeden biter:

```bash
printf '999\n' | java -cp bin veriOdev1.Main
```
