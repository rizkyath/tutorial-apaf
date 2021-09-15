# Tutorial APAP
## Authors
* **MUHAMMAD RIZKY ATHALLAH** - *1906398982* - *B*

---
## Tutorial 2
### What I have learned today
### Pertanyaan 1
Setelah dirun, masih terjadi error karena template belum dibuat. Sedangkan pada implementasi kode
pada Controller, method untuk mapping ke link tersebut sudah mereturn nama html. Namun html belum 
dibuat.
### Pertanyaan 2
<b>@Autowired</b> merupakan implementasi dari konsep Dependecy Injections. Dengan menggunakan autowired,
data yang didapatkan dari apa yang diimplementasikan pada bagian controller akan langsung tersambung 
dengan instance yang berada pada bagian service. Pada kasus ini, ketika kita melakukan perubahan instance
travelAgensiService pada bagian controller, maka data tersebut akan dibagikan ke bagian service sehingga
data yang sudah disimpan pada request-request sebelumnya tetap tersimpan.
### Pertanyaan 3
Setelah mengakses link tersebut untuk melakukan add Agensi, terjadi error karena
parameter yang diberikan pada link tersebut tidak terdapat noTelepon yang 
seharusnya pada implementasi kode harus tertera pada request parameter.
### Pertanyaan 4
Untuk melihat detail sebuah travel agensi, kita dapat menggunakan mapping yang 
sesuai,yaitu "/agensi/view?idAgensi=xx", sehingga link yang bisa digunakan:
http://localhost:8080/agensi/view?idAgensi=1 karena Papa APAP memiliki idAgensi 1
### Pertanyaan 5
link: http://localhost:8080/agensi/add?idAgensi=2&namaAgensi=Mama%20AMAM&alamat=Universitas%20Indonesia&noTelepon=123xxx <br>
Setelah Add:
![img.png](img.png)
Tampak View All
![img_1.png](img_1.png)

### Latihan
1. Fitur: Mencari daftar agensi berdasarkan nama Agensi.
Diakses pada link http://localhost:8080/agensi/view/nama-agensi/Papa%20APAP
![img_2.png](img_2.png)
2. Fitur: Meng-update idAgensi Agensi. Implementasi fungsi update dibuat pada bagian Service. Diakses pada link
http://localhost:8080/agensi/update/1/id-agensi/2
Bukti id berubah:<br>
a. Menambahkan data dengan id 1
![img_3.png](img_3.png)
![img_4.png](img_4.png)
b. Meng-update id dari data dengan id 1
![img_5.png](img_5.png)
c. Id 1 telah berubah jadi 2
![img_6.png](img_6.png)
3. Remove data by id
a. Menambahkan data dengan id 1
![img_7.png](img_7.png)
![img_8.png](img_8.png)
b. Melakukan remove, dan hasil remove bisa dilihat dengan hilangnya data pada viewAll
![img_9.png](img_9.png)



---
## Tutorial 1
### What I have learned today
Pada Tutorial 1 kali ini, saya telah cukup memahami cara menggunakan Git secara lebih mendalam.
Selain itu, saya juga telah mempelajari bagaimana cara membuat sebuah project Spring Boot, yang
diawali dengan download di Spring Initializer sampai ke konsep dari Spring Boot itu sendiri.
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
Issue Tracker merupakan isu yang bisa saja ditemukan oleh programmer dalam melakukan 
pemrogramannya. Dengan issue tracker, segala macam masalah yang dialami programmer bisa dituliskan
sehingga lebih terdokumentasi baik masalah yang dihadapi, maupun solusi terhadap permasalahan tersebut.
2. Apa perbedaan dari git merge dan git merge --squash?
Dalam melakukan merge, terdapat lebih dari satu parent branch. Ketika kita melakukan Git merge,
maka hasil commit dari merge tersebut memiliki dua buat parent. sedangkan, git merge --squash
hanya akan mencatat parent targetnya saja, sehingga merge tersebut hanya memiliki 1 parent.
3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan
suatu aplikasi?
Dengan menggunakan Git, proses pemrograman dari awal hingga akhir terdokumentasi dengan jelas,
baik dari bagian penyelesaian solusi programming, bug, dan lain-lain. Github juga memudahkan 
programmer untuk melakukan kerja sama tim dalam programming, sehingga suatu projek bisa 
lebih mudah dikerjakan bersama-sama menggunakan github.
### Spring
4. Apa itu library & dependency?
Library merupakan file java yang sudah terdokumentasi yang bisa digunakan untuk membantu 
programmer dalam menyelesaikan masalah. Dependency adalah hubungan yang dibutuhkan sebuah kode
dengan kode yang lain agar suatu program bisa dijalankan sesuai ekspektasi.
5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
Maven adalah tool untuk me-manage project yang akan kita buat yang memudahkan kita dalam membuat
project aplikasi. Dengan menggunakan maven, kita dipermudah dalam menambahkan berbagai dependencies
daru project yang akan dibuat. Terdapat alternatif dari Maven yang bisa digunakan, yaitu Postman, TeamCity, 
CircleCI, Red Hat Ansible Automation Platform, dan lain-lain.
6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring
framework?
Spring framework bisa digunakan untuk membuat aplikasi berbasis java. 
7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
menggunakan @RequestParam atau @PathVariable?
@RequestParam digunakan untuk mendapatkan parameter Query dari URI.
@PathVariable digunakan untuk value parameter yang dinamis.
### What I did not understand
Saya masih belum mengerti bagaimana cara kerja Maven sehingga bisa menghubungkan antara satu 
dependency dengan dependencies yang lain.
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-_check_ apabila Anda
sudah mengerti dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti)
- [ ] Kenapa saya harus belajar APAP?
- [x] Kenapa?
Karena 
