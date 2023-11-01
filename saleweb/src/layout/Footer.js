import { Alert } from "react-bootstrap";
import "./Footer.css"

const Footer = () => {
    return <>
    <hr className="seperate" />
    <div className="footer-home">
                    <div>
                        <div className="information">THÔNG TIN CHUNG</div>
                        <ul>
                            <li>Địa chỉ: 35 – 37 Hồ Hảo Hớn, Phường Cô Giang, Quận 1, TP. HCM.</li>
                            <li>Điện thoại: 028-38364748.</li>
                            <li>Fax: 028-39207639 hoặc 028-39207640.</li>
                            <li>E-mail: xxx@gmail.com</li>
                        </ul>
                    </div>
                    <div>
                        <div className="information">CÁC CHI NHÁNH</div>
                        <ul>
                            <li>Địa điểm 1: 97 Võ Văn Tần, P. Võ Thị Sáu, Q. 3, TP. Hồ Chí Minh.</li>
                            <li>Địa điểm 2: 35-37 Hồ Hảo Hớn, P. Cô Giang, Q. 1 , TP. Hồ Chí Minh</li>
                            <li>Địa điểm 3: 371 Nguyễn Kiệm, P. 3, Q. Gò Vấp, TP. Hồ Chí Minh.</li>
                            <li>Địa điểm 4: 02 Mai Thị Lựu, P. Đa Kao, Q. 1, TP. Hồ Chí Minh.</li>
                            <li>Địa điểm 5: 68 Lê Thị Trung, P. Phú Lợi, TP. Thủ Dầu Một, Tỉnh Bình Dương.</li>
                            <li> Địa điểm 6: Đường số 9, P. Long Bình Tân, TP. Biên Hòa, Tỉnh Đồng Nai.</li>
                            <li>Địa điểm 7: Số 04 đường Tân Định, P. Ninh Hiệp, Thị Xã Ninh Hòa,Tỉnh Khánh Hòa.</li>
                        </ul>
                    </div>
                    <div className="footer__col">
                        <ul className="f-listmenu">
                            <li><a rel="nofollow" href="/tin-tuc/tat-tan-tat-ve-chuong-trinh-uu-dai-app-qua-tang-vip-link-tai-va-cach-su-dung-1481677#gioithieu">Tích điểm Quà tặng VIP</a></li>
                            <li><a rel="nofollow" href="/lich-su-mua-hang">Lịch sử mua hàng</a></li>
                            <li><a rel="nofollow" href="/tra-gop">Tìm hiểu về mua trả góp</a></li>
                            <li><a rel="nofollow" href="/bao-hanh">Chính sách bảo hành</a></li>
                            <li className="hidden"><a rel="nofollow" href="/chinh-sach-bao-hanh-san-pham">Chính sách đổi trả</a></li>
                            <li className="hidden"><a rel="nofollow" href="/giao-hang">Giao hàng &amp; Thanh toán</a></li>
                            <li className="hidden"><a rel="nofollow" href="/huong-dan-mua-hang">Hướng dẫn mua online</a></li>
                            <li className="hidden"><a rel="nofollow" href="https://hddt.thegioididong.com">In hóa đơn điện tử</a></li>
                            <li className="hidden"><a rel="nofollow" href="/chinh-sach-xu-ly-du-lieu-ca-nhan">Chính sách xử lý dữ liệu cá nhân</a></li>
                            <li className="hidden"><a rel="nofollow" href="/noi-quy-cua-hang">Nội quy cửa hàng</a></li>
                            <li className="hidden"><a rel="nofollow" href="/chinh-sach-khui-hop-apple">Chính sách khui hộp sản phẩm Apple</a></li>
                        </ul>
                    </div>
    </div>
    <div className="text-footer">
        Sale Application &copy; 2023
      </div>
      </>
}

export default Footer;