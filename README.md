# Dự án Hệ thống Xác thực Người dùng Bảo mật cao

Dự án này xây dựng một hệ thống xác thực mạnh mẻ và an toàn cho các ứng dụng web và di động. Hệ thống sử dụng các công nghệ hiện đại để đảm bảo an toàn cho tài khoản người dùng, đồng thời mang lại trải nghiệm mượt mà và linh hoạt.

---

## ✨ Các tính năng chính

* **Xác thực Email khi Đăng ký**: Đảm bảo người dùng cung cấp địa chỉ email hợp lệ bằng cách gửi một liên kết xác thực để kích hoạt tài khoản.
* **Xác thực cơ bản & Nâng cao**: Hỗ trợ đăng nhập bằng `username/password` truyền thống và tích hợp **Xác thực hai yếu tố (2FA)** để tăng cường bảo mật.
* **Quản lý phiên với JWT**: Sử dụng **JSON Web Token (JWT)** để quản lý phiên đăng nhập một cách hiệu quả và an toàn (stateless).
* **Xác thực hai yếu tố (2FA)**: Tích hợp chuẩn **TOTP (Time-based One-Time Password)**, cho phép người dùng sử dụng các ứng dụng như Google Authenticator, Authy để bảo vệ tài khoản.
* **Gia hạn phiên đăng nhập (Refresh Token)**: Cho phép tự động làm mới phiên đăng nhập mà không yêu cầu người dùng đăng nhập lại, cải thiện trải nghiệm người dùng.
* **Bảo mật API**: Tất cả các API yêu cầu tài nguyên đều được bảo vệ thông qua cơ chế xác thực JWT.

---

## 🛠️ Các thành phần chính

| Thành phần                          | Mô tả chi tiết                                                                                                                       |
| :---------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------- |
| **User Authentication**             | Xác thực người dùng thông qua `username` và `password`.                                                                              |
| **Email Service**                   | Gửi email chứa liên kết xác thực khi người dùng đăng ký tài khoản mới.                                                               |
| **JWT Token Service**               | Chịu trách nhiệm cấp phát `access token` (ngắn hạn) và `refresh token` (dài hạn) sau khi xác thực thành công.                        |
| **Two-Factor Authentication (2FA)** | Tích hợp và xác minh mã OTP từ các ứng dụng như Google Authenticator.                                                                |
| **Refresh Token Management**        | Lưu trữ và quản lý `refresh token` một cách an toàn trong cơ sở dữ liệu để gia hạn phiên đăng nhập.                                  |
| **Quản lý người dùng**              | Lưu trữ thông tin người dùng, trạng thái tài khoản (đã xác thực/chưa xác thực), trạng thái 2FA, và khóa bí mật 2FA (đã được mã hóa). |
| **API bảo mật**                     | Các endpoints của ứng dụng được bảo vệ, yêu cầu một `access token` hợp lệ để truy cập.                                               |

---

## ⚙️ Quy trình hoạt động

### Quy trình Đăng ký (Registration Flow)

1. Người dùng điền thông tin đăng ký (ví dụ: `username`, `email`, `password`).
2. Hệ thống tạo một tài khoản mới với trạng thái **chưa được xác thực (unverified)**.
3. Hệ thống tạo một token xác thực duy nhất và gửi một email chứa liên kết xác thực tới địa chỉ email của người dùng.
4. Người dùng kiểm tra email và nhấp vào liên kết xác thực.
5. Hệ thống xác thực token. Nếu hợp lệ, tài khoản của người dùng sẽ được kích hoạt.
6. Giờ đây, người dùng có thể đăng nhập vào hệ thống.

### Quy trình Đăng nhập (Login Flow)

Hệ thống hỗ trợ hai luồng đăng nhập chính:

**1. Đăng nhập thông thường (2FA chưa được kích hoạt):**

1. Người dùng gửi `username` và `password`.
2. Hệ thống xác thực thông tin.
3. Nếu thành công, hệ thống trả về `JWT access token` và `refresh token`.

**2. Đăng nhập với 2FA (đã được kích hoạt):**

1. Người dùng gửi `username` và `password`.
2. Hệ thống xác thực thông tin.
3. Nếu thành công, hệ thống yêu cầu người dùng nhập mã OTP.
4. Người dùng nhập mã OTP từ ứng dụng (ví dụ: Google Authenticator).
5. Hệ thống xác thực mã OTP.
6. Nếu mã OTP hợp lệ, hệ thống trả về `JWT access token` và `refresh token`.

---

## 🚀 Công nghệ sử dụng

* **Backend**: `Java Spring Boot`
* **Gửi Email**: `Spring Boot Starter Mail`
* **Xác thực JWT**: Thư viện `io.jsonwebtoken/jjwt`
* **2FA TOTP**: Thư viện `dev.samstevens/totp`
* **Cơ sở dữ liệu**: `MySQL`

  * **Database Schema**: [Xem sơ đồ tại đây](https://drawsql.app/teams/ad-87/diagrams/auth-service)
* **Client-side 2FA**: `Google Authenticator` hoặc các ứng dụng tương thích.

### Đồng thời sử dụng các công nghệ backend Spring phổ biến:

* **Spring Security**: Bảo vệ API, cấu hình xác thực và phân quyền.
* **Spring Data JPA**: Truy vấn và thao tác cơ sở dữ liệu dựa trên ORM.
* **Spring Validation**: Xác thực dữ liệu đầu vào (form, request body).
* **Spring Web (Spring MVC)**: Xây dựng RESTful API cho backend.
* **Lombok**: Tự động sinh getter, setter, constructor,... giảm thiểu boilerplate code.
* **MapStruct** hoặc **ModelMapper**: Ánh xạ du lieu giữa entity và DTO nhanh chóng.

---

## 🌟 Lợi ích của dự án

- **Tăng cường bảo mật**: Yêu cầu xác thực email khi đăng ký và bảo vệ tài khoản với 2FA.
- **Trải nghiệm người dùng tốt hơn**: Kiến trúc stateless với JWT giúp hệ thống dễ dàng mở rộng và không làm gián đoạn trải nghiệm người dùng.
- **Quản lý phiên linh hoạt**: Refresh token cho phép người dùng duy trì đăng nhập trong thời gian dài một cách an toàn.
- **Khả năng tương thích cao**: Dễ dàng tích hợp vào nhiều loại ứng dụng khác nhau như Web, Mobile, và các hệ thống Microservices.

---

## 🔮 Mở rộng & Nâng cấp trong tương lai

- **Tích hợp OAuth 2.0**: Hỗ trợ đăng nhập thông qua các nhà cung cấp bên thứ ba như Google, Facebook, GitHub.
- **Hỗ trợ đa thiết bị**: Cho phép người dùng đăng nhập và quản lý phiên trên nhiều thiết bị cùng lúc.
- **Phân quyền chi tiết (RBAC)**: Xây dựng hệ thống quản lý vai trò và quyền hạn (Role-Based Access Control) để kiểm soát truy cập tài nguyên.
- **Giám sát và cảnh báo**: Tích hợp hệ thống giám sát để phát hiện và cảnh báo các hoạt động đăng nhập bất thường.