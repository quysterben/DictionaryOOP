# DICTIONARY APP

> Ứng dụng từ điển làm cho bài tập lớn số một môn Lập Trình Hướng đối tượng.
## Nhóm chúng tôi có :
  - Trương Khôi Nguyên.*Github : https://github.com/TruongKhoiNguyen*
  - Nguyễn Văn Thịnh. *Github : https://github.com/ThinhNguyen1102*
  - Hoàng Sỹ Quý. *Github : https://github.com/quysterben*

    Link demo youtube: https://www.youtube.com/watch?v=YJslMPfQx6M

## Tính năng chính : 
  - Tra cứu từ (có tính năng đọc từ mà người dùng muốn).
  - Có bookmark để lưu những từ cần nhớ.
  - Dịch từ và dịch câu (sử dụng Google API - Google translate).
  - Thêm, chỉnh sửa và xóa từ (đối với những từ được thêm vào từ người dùng).

## Tính năng đặc biệt :
  - **Spell Correction** : tự động tra từ dựa theo input trong trường hợp input không giống với database.
  - **Lookup** : Tính năng tra từ (với sự hỗ trợ của Spell Correction). Các từ hiển thị theo lịch sử tra.
  - **Translate** : Dịch từ và văn bản với các ngôn ngữ khác nhau.
  - **Edit** : Add, Update, Delete các từ (đối với những từ được thêm từ người dùng).
  - **CSS** : tạo các hiệu ứng, màu sắc thân thiện với người dùng nhất.

## Chúng tôi sử dụng :
  - Ngôn ngữ chính : Java (16)
  - Thư viện đồ họa : JavaFx (có sử dụng thêm thư viện Jfoenix).
  - Sử dụng database SQLite : 108860 từ gồm 1 table dictionary (av) chính 
    và 2 table history và bookmark tham chiếu đến bằng primaryKey id dictionary (av)
  - Sử dụng Google Api hỗ trợ dịch từ và văn bản.
  - Sử dụng Free TTS hỗ trợ đọc từ.
