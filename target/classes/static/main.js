
function deleteFaculty(path) {
    if (confirm("TẤT CẢ dữ liệu liên quan sẽ bị xóa, Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Hệ thống có lỗi! Vui lòng quay lại sau!");
        });
    }
}