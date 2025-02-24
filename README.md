# Tugas kecil 1 Strategi Algoritma

## IQ puzzler solver
Made by Aryo Bama Wiratama, 13523088

## Description
IQ Puzzler adalah permainan puzzle berbasis grid di mana pemain harus menempatkan sejumlah bentuk (pieces) ke dalam area tertentu tanpa tumpang tindih. Solver untuk permainan ini dapat dibuat dengan pendekatan brute force, yang mencoba semua kemungkinan penempatan pieces hingga menemukan solusi yang valid.

Berikut penjelasan algoritma bruteforce dalam mencari solusi pada gim iq puzzler:
- Misalkan terdapat P buah piece dan papan berukuran n x m
-	Susunlah piece tersebut. Penyusunan piece tidak berdasarkan syarat apapun.
-	Ambil satu piece kemudian letakkan pada papan mulai dari titik O (0,0).
-	Kemudian ambil piece selanjutnya. Coba letakkan piece dalam papan dimulai dari titik O. Apabila suatu titik telah ditempati oleh piece lain, maju ke titik selanjutnya.
-	Lakukan hal no.4 hingga mencapai piece terakhir.
-	Apabila dalam proses no 4 dan 5 terdapat piece yang tidak menemukan tempat kosong pada papan, coba lakukan semua gerakan rotasi dan refleksi.
-	Apabila langkah no 6 juga tidak berhasil, lakukan backtracking dengan cara melakukan rotasi dan refleksi pada piece sebelumnya. Kemudian letakkan kembali piece yang gagal ditempatkan.
-	Ulangi langkah 1-7 hingga semua kemungkinan telah dicoba
