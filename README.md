**Nama**: Wirya Dharma Kurnia <br />
**NPM**: 2306152115 <br />
**Kelas**: ADPRO B


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

## Link Deployment
https://raspy-herminia-wiryaa23-5700da17.koyeb.app/

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

