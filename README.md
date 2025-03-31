b1: tạo bảng database "contacts_test"
b1: Làm theo bước này tạo thư mục giống như này -> /XAMPP/xamppfiles/htdocs/contacts_api/get_contacts.php
{
<?php
header('Content-Type: application/json');

// Thay đổi thông tin kết nối cơ sở dữ liệu của bạn
$servername = "localhost"; // hoặc địa chỉ IP của máy chủ
$username = "root"; // tên người dùng (thay đổi nếu cần)
$password = ""; // mật khẩu (thay đổi nếu cần)
$dbname = "contacts_test"; // tên cơ sở dữ liệu

// Tạo kết nối
$conn = new mysqli($servername, $username, $password, $dbname);

// Kiểm tra kết nối
if ($conn->connect_error) {
    // Nếu kết nối không thành công, trả về thông báo lỗi
    die(json_encode(["error" => "Kết nối cơ sở dữ liệu không thành công: " . $conn->connect_error]));
}

// Nếu kết nối thành công, thực hiện truy vấn
$user_id = $_GET['user_id'];
$sql = "SELECT * FROM contacts WHERE user_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $user_id);
$stmt->execute();
$result = $stmt->get_result();

$contacts = [];
if ($result->num_rows > 0) {
    // Lấy dữ liệu từ kết quả truy vấn
    while($row = $result->fetch_assoc()) {
        $contacts[] = $row;
    }
    echo json_encode($contacts);
} else {
    echo json_encode([]);
}

$stmt->close();
$conn->close();
?>
}
b3: Chạy được link này: http://localhost/contacts_api/get_contacts.php?user_id=user1
b4: Nhập code 
b5: Run




