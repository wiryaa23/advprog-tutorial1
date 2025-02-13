*Nama*: Wirya Dharma Kurnia <br />
*NPM*: 2306152115 <br />
*Kelas*: ADPRO B

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