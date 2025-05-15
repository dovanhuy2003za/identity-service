1. Tổng quan dự án
Dự án xây dựng hệ thống xác thực người dùng bảo mật cao dành cho ứng dụng web hoặc mobile, sử dụng:

JWT (JSON Web Token) để cấp và quản lý token phiên đăng nhập.

Two-Factor Authentication (2FA) với chuẩn TOTP (Time-based One-Time Password), sử dụng Google Authenticator hoặc các app tương tự để tăng cường bảo mật.

Hỗ trợ đăng nhập thông thường (username/password) và mở rộng 2FA cho người dùng nâng cao.

Hỗ trợ Refresh Token để gia hạn phiên đăng nhập mà không cần đăng nhập lại.

2. Các thành phần chính
Thành phần	Mô tả chi tiết
User Authentication	Xác thực bằng username và password cơ bản.
JWT Token Service	Cấp access token (JWT) và refresh token, quản lý phiên đăng nhập.
Two-Factor Authentication (2FA)	Tích hợp 2FA bằng Google Authenticator, tạo và kiểm tra mã OTP theo chuẩn TOTP.
Refresh Token Management	Lưu trữ và quản lý refresh token trên database, hỗ trợ làm mới token khi hết hạn.
Quản lý người dùng	Lưu trữ thông tin người dùng, trạng thái 2FA, secret key 2FA (được mã hóa).
API bảo mật	Các API bảo vệ bằng xác thực JWT, kiểm tra token và quyền truy cập.

3. Quy trình đăng nhập (Login flow)
Người dùng gửi yêu cầu đăng nhập với username và password.

Nếu xác thực thành công và 2FA chưa bật:

Trả về JWT access token và refresh token để dùng cho các request sau.

Nếu 2FA đã bật:

Trả về yêu cầu nhập mã OTP.

Người dùng nhập mã OTP từ Google Authenticator app.

Backend kiểm tra mã OTP đúng, sau đó mới tạo và trả về JWT access token và refresh token.

4. Công nghệ sử dụng
Backend: Java Spring Boot

Xác thực JWT: thư viện jjwt

2FA TOTP: thư viện dev.samstevens.totp

Cơ sở dữ liệu: Mysql(database schema: https://drawsql.app/teams/ad-87/diagrams/auth-service )

Google Authenticator: làm app tạo mã OTP trên điện thoại

Quản lý token: Access token ngắn hạn (ví dụ 15 phút), Refresh token dài hạn (ví dụ 7 ngày)

5. Lợi ích dự án
Tăng cường bảo mật: Không chỉ dựa vào mật khẩu mà còn xác minh qua mã OTP động.

Trải nghiệm người dùng: Dùng JWT giúp hệ thống không trạng thái (stateless), dễ mở rộng.

Quản lý phiên linh hoạt: Refresh token giúp kéo dài phiên đăng nhập an toàn.

Phù hợp với nhiều loại ứng dụng: Web, mobile, microservices.

6. Mở rộng & nâng cấp tương lai
Tích hợp thêm OAuth 2.0 (Google Sign-In, Facebook Login, v.v.)

Hỗ trợ đa thiết bị, đa phiên làm việc

Quản lý quyền truy cập chi tiết hơn (RBAC)

Tích hợp hệ thống giám sát và cảnh báo đăng nhập bất thường
