# Hướng dẫn cài đặt hệ thống SaleApp Backend
## Bước 1. Giải nén project
## Bước 2. Sử dụng một trong hai IDE là IntelliJ hoặc Eclipse
## Bước 3. Sử dụng MySQL để tạo cơ sở dữ liệu.
```
-Mở MySQL, tạo 1 database rỗng có tên saledb
```
```
-Trên Toolbar, chọn Server -> Data import -> Chọn Import from Self-Contained File -> Chọn file saledb.sql đã đính kèm trong project
```
```
-Ở Default Schema to be Imported To, chọn database vừa tạo là saledb.
```
```
-Nhấn Start Import
```
## Bước 4. Mở project SaleApp bằng IntelliJ
```
chọn src -> main -> java -> com.nmt.saleapp -> chuột phải vào SaleAppApplicaiton -> Run SaleAppApplicaiton
```
## Bước 5. Mở trình duyệt và gõ đường dẫn "http://localhost:8080"
## Bước 6. Đăng nhập với thông tin sau để test các chức năng
```
tài khoản: thanh3
```
```
mật khẩu: thanh3


# Hướng dẫn chạy hệ thống University Front-end
## Bước 1: Mở thư mục saleweb (nằm trong thư mục SaleApp đã clone về) bằng Visual Studio Code
## Bước 2. Trên toolbar, chọn Terminal -> new Terminal, trong hộp thoại terminal, gõ lệnh "npm install" để cài đặt
## Bước 3. Sau khi cài đặt xong, gõ tiếp lệnh "npm start" để chạy chương trình