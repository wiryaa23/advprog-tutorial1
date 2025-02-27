**Nama**: Wirya Dharma Kurnia <br />
**NPM**: 2306152115 <br />
**Kelas**: ADPRO B 

# Link Deployment
https://raspy-herminia-wiryaa23-5700da17.koyeb.app/

<details>
<summary><b>Tugas 1</b></summary>

# Refleksi 1

## Prinsip clean code yang telah diimplementasikan dalam kode:
- Penamaan yang bermakna
  Nama variabel dan function yang saya gunakan dalam kode bersifat representatif, seperti productName untuk nama produk, productId untuk ID produk, dan productQuantity untuk jumlah produk.
- Function fokus pada satu hal
  Function yang saya gunakan berfokus dalam mengerjakan suatu hal saja, sehingga mudah dipahami dan cenderung lebih mudah diperbaiki jika terjadi kesalahan. Contohnya function edit untuk melakukan perubahan pada informasi produk saja, tidak dapat menghapus produk.
- Comments
  Pada bagian file HTML, saya menambahkan beberapa komentar agar lebih mudah mengenali kode format tampilan. Misalnya dengan menambahkan komentar "Product Name" untuk kode yang menampilkan bagian produk name. Hal ini dilakukan karena kode HTML cenderung lebih sulit dikenali mengingat banyaknya aspek yang perlu digunakan.
- Layout dan formatting
  Saya menggunakan baris kosong untuk memisahkan beberapa bagian dalam kode, seperti deklarasi package, import, function, dan lain-lain. Indentasi yang digunakan juga konsisten dan rapi sehingga mudah dibaca.
- Input data validation
  Pada bagian edit product, saya menambahkan validasi pada bagian product quantity agar jumlahnya tidak bisa kurang dari 0.

## Cara meningkatkan kode:
- Menambahkan pesan konfirmasi sebelum menghapus product pada fitur edit-product.
- Menggunakan UUID untuk productId daripada menggunakan integer.
- Mengimplementasikan function delete dengan method POST/DELETE untuk alasan keamanan. Saat ini, function delete masih menggunakan method GET sehingga cenderung tidak aman mengingat ini bisa diakses langsung via URL.

# Refleksi 2

## 1
- Setelah membuat unit test, saya merasa mendapat pemahaman mengenai pentingnya testing dalam mengembangkan sebuah sistem. Saya menjadi lebih yakin mengenai fungsionalitas kode saya.
- Tidak ada jumlah tertentu mengenai unit test yang harus dibuat, namun akan lebih baik jika terdapat testing untuk setiap method/bagian kecil dari sistem.
- Jika kita memiliki 100% code coverage, itu tidak berarti kode kita tidak memiliki bug atau error sama sekali. Hal tersebut hanya menunjukkan sejauh mana kode kita diuji.

## 2
- Jika kita membuat functional test baru yang serupa, maka akan banyak variabel dan proses setup yang direplikasi. Hal ini tidak sesuai dengan prinsip clean code karena banyak terjadi pengulangan kode sehingga dapat menurunkan kualitas kode.
- Untuk mengatasi masalah tersebut, kita dapat menginisialisasi functional test dasar yang dapat digunakan ulang berkali-kali.

</details>

<details>
<summary><b>Tugas 2</b></summary>

## Code quality issue(s) yang diperbaiki:
- Unused parameter pada `ProductController.java`

  Pada berkas `ProductController.java`, fungsi `createProductPost` dan `deteleProduct` menerima parameter dengan tipe data Model. Parameter ini sebenarnya tidak diperlukan dalam operasi kode yang terjadi di dalam fungsi. Karena itu, saya akhirnya menghapus parameter bertipe Model dari kumpulan parameter kedua fungsi tersebut.
- Unused import pada `ProductController.java`

  Pada berkas `ProductController.java`, terdapat baris import yaitu `import org.springframework.web.bind.annotation.*` yang menyebabkan adanya import yang tidak digunakan. Hal ini karena baris import tersebut melakukan import terhadap semua elemen dalam package tersebut, meskipun dalam jalannya kode saya hanya membutuhkan beberapa seperti `GetMapping`, `PostMapping`, `RequestMapping`, `ModelAttribute`, dan `PathVariable`. Maka dari itu, saya kemudian menghapus baris `import org.springframework.web.bind.annotation.*` dan melakukan import yang saya butuhkan secara satu persatu.
- Unnecessary modifier pada `ProductService.java`

  Pada berkas `ProductService.java`, saya memberikan modifier *public* pada method `create()`, `delete()`, dan `findAll()`. Hal ini tidak dibutuhkan karena secara *default* Java sudah mengatur agar method dalam *interface* bersifat *public*. Maka dari itu, saya menghapus modifier *public* dari ketiga method tersebut agar kode menjadi lebih bersih.


## Apakah implementasi sekarang telah sesuai dengan definisi Continuous Integration and Continuous Deployment (CI/CD)?

Alur kerja yang diimplementasikan saat ini telah cukup memenuhi definisi dari CI/CD karena telah mencakup tahapan yang dibutuhkan, seperti proses kode, pengujian (*testing*) otomatis, *review*, serta *deployment*. Github Workflow berperan besar dalam hal ini, yaitu sebagai berikut:
- `ci.yml` yang menjalankan *unit test* setiap adanya *push* atau *pull request* sehingga kita dapat memastikan perubahan pada kode tidak langsung merusak fungsionalitas kode yang sebelumnya telah ada.
- `pmd.yml` yang melakukan *review* pada kode untuk mengurangi potensi kesalahan dan menjaga kualitas kode.
- `scorecard.yml` yang melakukan analisis untuk menjaga keamanan kode.

Setelah melalui proses di atas, perubahan kode akan di-*merge* ke branch utama seperti `main` dan akan di-*build* secara otomatis oleh `Koyeb` hingga website dapat berjalan dengan lancar. Dengan demikian, maka semua proses CI/CD telah diimplementasikan dengan cukup baik.

## Code Coverage
![Image](https://github.com/user-attachments/assets/b08bf46f-ad12-4658-9909-39fab502da22)

</details>

<details open>
<summary><b>Tugas 3</b></summary>

# Refleksi 3

## Jelaskan prinsip apa yang diterapkan dalam project kali ini!
- Single Responsibility Principle (SRP)

Awalnya, `CarController` berada di tempat yang sama dengan `ProductController` yaitu pada `ProductController.java` dan menggunakan *extends* ke `ProductController`. Padahal, kedua *class* tersebut memiliki tugas yang berbeda. Maka dari itu, saya memisahkan keduanya dengan memindahkan `CarController` ke `CarController.java` sehingga tidak lagi menggunakan *extends*. Dengan begitu, kini setiap *class* hanya memiliki satu tanggung jawab sesuai dengan *Single Responsibility Principle*.

- Open-Closed Principle (OCP)

Struktur dari *controller* yang ada pada program memungkinkan adanya penambahan *route* atau *endpoint* tanpa perlu mengubah fungsionalitas yang telah ada, sehingga sistem bersifat fleksibel untuk pengembangan lebih lanjut tanpa memodifikasi kode yang telah berjalan dengan baik.

- Interface Segregation Principle (ISP)

Sistem dibagi menjadi beberapa *interface* yang spesifik sesuai tugasnya, sehingga saya memastikan bahwa sistem hanya menyediakan *method* yang benar-benar dibutuhkan dan relevan. Dengan demikian, kode akan menjadi lebih *maintainable*.

- Dependency Inversion Principle (DIP)

Pada mulanya, `carservice` memiliki ketergantungan langsung pada kelas yang sifatnya *concrete*. Menurut *Dependency Inversion Principle*, ketergantungan seharusnya diarahkan pada *interface* yang sifatnya abstrak, bukan langsung dengan implementasi *concrete*. Maka, saya mengubah `carservice` untuk bergantung pada *interface* `CarService`, daripada langsung pada `CarServiceImpl`.


## Jelaskan keuntungan dalam menerapkan prinsip SOLID dalam project ini dengan contoh!

Menerapkan prinsip SOLID dapat membantu dalam pengembangan perangkat lunak, khususnya dalam menyusun arsitektur perangkat lunak berorientasi objek yang baik, mudah beradaptasi terhadap perubahan, dan mudah dikelola ke depannya (*manageable*).
- Dengan menerapkan SRP, kode menjadi lebih jelas dan mudah dipahami, khususnya bagi *programmer* lain. Hal ini dikarenakan sebuah *class* telah dikustomisasi untuk suatu tugas tertentu. Misalnya pada `CarController` yang hanya bertugas untuk mengurus *logic* terkait objek mobil, sehingga orang lain dapat memahami kode dengan baik tanpa bingung mengenai objek lainnya.
- Dengan menerapkan OCP, kita dapat mengembangkan sistem tanpa mengubah kode lama. Hal ini dilakukan dengan menambah kode baru dengan bersifat terbuka terhadap ekstensi, namun tertutup pada modifikasi. Misalnya jika kita ingin menambahkan `PlaneController`, maka kita dapat membuat *class* baru tanpa mengubah kode `CarController` sehingga mengurangi risiko adanya *bug* pada kode yang sudah berjalan dengan baik.
- Dengan menerapkan LSP, maka polimorfisme pada program akan cenderung aman karena *subclass* dapat menggantikan *superclass* tanpa mengubah perilaku sistem. Karena itu, tidak akan ada perilaku tak wajar yang muncul ketika kita melakukan perubahan pada *subclass*.
- Dengan menerapkan ISP, kode akan menjadi lebih mudah dipelihara (*maintainable*) karena *method* yang diimplementasikan pada suatu *class* hanyalah *method* yang dibutuhkan saja. Misalnya pada `CarController`, ada *method-method* seperti *create*, *carList*, *edit*, dan *delete* yang berhubungan dengan objek mobil; dan tidak ada *method* seperti *eat* atau *sleep* yang tidak ada hubungannya dengan objek mobil.
- Dengan menerapkan DIP, kode akan menjadi lebih modular dan fleksibel karena implementasinya bergantung pada abstraksi, bukan implementasi konkret. Misalnya pada `carservice` yang bergantung pada *interface* `CarService` dan bukan `CarServiceImpl`. Ini juga akan membuat pengujian khususnya *unit testing* menjadi lebih mudah karena dapat mengganti dependensi dengan `mock`.

## Jelaskan kerugian dalam tidak menerapkan prinsip SOLID dalam project ini dengan contoh!

Jika kita tidak menggunakan prinsip SOLID, maka kode akan menjadi sulit dipelihara, tidak fleksibel, rentan terhadap bug, dan bahkan sulit dikembangkan dalam jangka panjang.
- Tanpa menerapkan SRP, kode akan menjadi sulit dibaca dan dipahami khususnya oleh *programmer* lain. Kode juga akan menjadi tidak relevan. Misalnya jika `CarController` juga menangani *logic* pemesanan makanan, maka orang lain akan kebingungan untuk memahami kode tersebut dan kesulitan jika ingin melakukan perubahan tanpa merusak kode yang telah berfungsi dengan baik.
- Tanpa menerapkan OCP, maka penambahan kode baru misalnya untuk fitur baru dapat merusak kode lama yang telah berfungsi dengan baik. Contohnya jika kita ingin menambahkan `PlaneController` dan harus mengubah `CarController` juga untuk penyesuaian, kita mungkin saja tanpa sadar menambahkan *bug* pada bagian `CarController` sehingga mengganggu kestabilan sistem.
- Tanpa menerapkan LSP, maka polimorfisme pada kode tidak akan berjalan dengan baik karena *subclass* bisa saja menyebabkan *bug* ketika bertugas menggantikan *superclass*. Hal ini dapat menyebabkan perilaku kode yang tidak sesuai keinginan sehingga mengharuskan banyak pengecekan terhadap `instanceof`.
- Tanpa menerapkan ISP, suatu *class* bisa saja mengimplementasikan *method* yang tidak dibutuhkan atau tidak relevan. Contohnya jika `CarController` memiliki *method* *eat* atau *sleep* yang tidak ada hubungannya dengan objek mobil. Maka, hal tersebut akan membuat kode menjadi tidak efisien dan mempersulit kerja sistem.
- Tanpa menerapkan DIP, kode akan langsung bergantung pada implementasi yang sifatnya konkret. Contohnya adalah jika `CarController` langsung bergantung pada `CarServiceImpl` daripada `CarService`. Hal ini akan menyebabkan ketergantungan yang tinggi antarkomponen sistem, sehingga menyebabkan pengujian kode khususnya *unit test* menjadi lebih sulit dan rumit.

</details>

