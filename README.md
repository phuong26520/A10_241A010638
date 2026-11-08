Lab A10 - GridView, RecyclerView và ViewPager2 - Album ảnh

Thông tin sinh viên: Nguyễn Hoài Phương - MSSV 241A010638 - Lớp LTTTBDD - Môn INT4211.

Mô tả ngắn gọn

App Album ảnh hiển thị 8 ảnh bằng RecyclerView dạng lưới. Có thể đổi số cột, chạm vào ảnh để mở màn hình xem ảnh lớn và vuốt ngang để chuyển ảnh. Bộ đếm cho biết ảnh hiện tại trên tổng số 8 ảnh.

Cấu trúc chính

app/src/main/java/vn/edu/vhu/ltdd/a10gallery/: các file MainActivity.java, AnhAdapter.java, AnhPagerAdapter.java và ViewerActivity.java.
app/src/main/res/layout/: giao diện lưới, một ô ảnh, màn hình xem ảnh và một trang ảnh lớn.
app/src/main/res/drawable/: 8 ảnh album anh1.jpg đến anh8.jpg.

Các chức năng đã làm

- Hiển thị 8 ảnh bằng RecyclerView và GridLayoutManager.
- Dùng ViewHolder và đủ 3 hàm của RecyclerView.Adapter.
- Có nút đổi số cột từ 2 đến 4 cột.
- Chạm ảnh mở ViewerActivity đúng vị trí ảnh đã chọn.
- Dùng ViewPager2 để vuốt ngang giữa các ảnh.
- Hiển thị bộ đếm vị trí ảnh và giữ ảnh không bị méo.
- Adapter gửi sự kiện bấm qua interface và kiểm tra NO_POSITION.

Bài nâng cao đã làm

NC2 - Đổi sang StaggeredGridLayoutManager

Bài yêu cầu đổi lưới thường sang lưới so le. Mình thêm nút Đổi kiểu lưới. Khi bấm nút, MainActivity đổi LayoutManager sang StaggeredGridLayoutManager với 2 cột theo chiều dọc. Các ô được sắp xếp so le giống kiểu Pinterest. Khi bấm lại, app chuyển về GridLayoutManager 3 cột. AnhAdapter và AnhViewHolder vẫn giữ nguyên vì Adapter chỉ phụ trách tạo và gán dữ liệu, còn LayoutManager phụ trách vị trí các ô.

NC4 - Nút xóa ảnh dùng notifyItemRemoved

Bài yêu cầu thêm chức năng xóa ảnh và cập nhật đúng vị trí bằng notifyItemRemoved. Mình đổi danh sách ảnh sang List<Integer>, thêm nút xóa ở mỗi ô và thêm callback xóa trong AnhAdapter. Khi bấm nút xóa, Adapter lấy vị trí hiện tại bằng getBindingAdapterPosition(), MainActivity xóa ảnh và chú thích tại vị trí đó, sau đó gọi adapter.notifyItemRemoved(viTri). RecyclerView chỉ cập nhật khu vực bị xóa và các ô phía sau dồn lên, có hiệu ứng trượt. Cách này không phải vẽ lại toàn bộ danh sách như notifyDataSetChanged().

Cách chạy và kiểm thử

Mở project A10_241A010638 trong Android Studio rồi chạy app. Kiểm tra lưới ảnh, nút đổi số cột, chạm ảnh thứ 5, vuốt ảnh và xoay ngang màn hình xem ảnh.
