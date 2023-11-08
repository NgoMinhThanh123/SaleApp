
import { useContext, useEffect, useState } from "react";
import { Button, Col, Form, Image, ListGroup, Modal, Row } from "react-bootstrap";
import Moment from "react-moment";
import cookie from "react-cookies";
import { Link, useParams } from "react-router-dom";
import { MyCartContext, MyUserContext } from "../App";
import Apis, { authApi, endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import "./ProductDetails.css"

const CommentRating = ({ rating }) => {
    const starValues = [1, 2, 3, 4, 5];
  
    return (
      <div className="comment-rating">
        {starValues.map((starValue) => (
          <span
            key={starValue}
            className={`star ${starValue <= rating ? 'filled' : ''}`}
          >
            ★
          </span>
        ))}
      </div>
    );
  };

const Rating = ({ rating, onRatingChange }) => {
    const starValues = [1, 2, 3, 4, 5];
  
    return (
      <div className="rating">
        {starValues.map((starValue) => (
          <span
            key={starValue}
            className={`star ${starValue <= rating ? 'filled' : ''}`}
            onClick={() => onRatingChange(starValue)}
          >
            ★
          </span>
        ))}
      </div>
    );
  };

const ProductDetails = () => {
    const [user, ] = useContext(MyUserContext);
    const [, cartDispatch] = useContext(MyCartContext);
    const {productId} = useParams();
    const [product, setProduct] = useState(null);
    const [comments, setComments] = useState(null);
    const [content, setContent] = useState();
    const [preview, setPreview] = useState(0);
    const [previewProduct, setPreviewProduct] = useState(0);
    const [rating, setRating] = useState(0);
    const [showReviewModal, setShowReviewModal] = useState(false);


    useEffect(() => {
        const loadProduct = async () => {
            let {data} = await Apis.get(endpoints['details'](productId));
            setProduct(data); 
        }

        const loadComments = async () => {
            let {data} = await Apis.get(endpoints['comments'](productId));
            setComments(data);
        }

        const loadPreviews = async () => {
            let {data} = await Apis.get(endpoints['previews'](productId));
            setPreview(data);
        }

        const loadPreviewProduct = async () => {
          let {data} = await Apis.get(endpoints['preview-product'](productId));
          setPreviewProduct(data);
      }

        loadProduct();
        loadComments();
        loadPreviews();
        loadPreviewProduct();
    }, []);

    

    const order = (product) => {
        cartDispatch({
            "type": "inc",
            "payload": 1
        });
        
        // lưu vào cookies
        let cart = cookie.load("cart") || null;
        if (cart == null)
            cart = {};
        
        if (product.id in cart) { // sản phẩm có trong giỏ
            cart[product.id]["quantity"] += 1;
        } else { // sản phẩm chưa có trong giỏ
            cart[product.id] = {
                "id": product.id,
                "name": product.name,
                "quantity": 1,
                "unitPrice": product.price
            }
        }

        cookie.save("cart", cart);

    }

    const handleRatingChange = (value) => {
        setRating(value);
      };
    
      const handleShowReviewModal = () => {
        setShowReviewModal(true);
      };
    
      const handleCloseReviewModal = () => {
        setShowReviewModal(false);
      };

      

    const addComment = () => {
        const process = async () => {
            let {data} = await authApi().post(endpoints['add-comment'], {
                "content": content, 
                "preview": rating, 
                "productId": product.id
            });

            console.log(rating);

            if (comments === null) {
                setComments([]);
            }
    
            if (preview === null) {
                setPreview([]);
            }

            setComments([...comments, data]);
            setPreview([...preview, data]);
            setContent("");
            setRating(0);
            handleCloseReviewModal();
        }

        process();
    }

    function formatPrice(price) {
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
    }
    
    if (product === null || comments === null)
        return <MySpinner /> ;

    let url = `/login?next=/products/${productId}`;
    return <>
         <h2 className="product product-title">{product.name}</h2>
         <div className="preview-avg">
          <span >Đánh giá: 
            <spam className="preview-avg-number"> {previewProduct.avgPreview}</spam>
            <span className="preview-avg-number preview-avg-icon">★</span> ({previewProduct.numberPreview} lượt đánh giá)
          </span>
          </div>
        <Row>
            <Col className="col-product-image" md={6} xs={6}>
                <Image className="product-image" src={product.image} rounded fluid />
            </Col>
            <Col md={5} xs={6}>
                <h3 className="product product-price">Giá: {formatPrice(product.price)}</h3>

                <div className="block__promo">
                    <div className="pr-top">
                        <p className="pr-txtb">Khuyến mãi  trị giá 200.000₫</p>
                        <i className="pr-txt">Giá và khuyến mãi dự kiến áp dụng đến 23:00 | 02/11</i>
                    </div>
                    <div className="pr-content">
                        <div className="pr-item">
                            <div className="divb t1">
                                <span className="nb">1.</span>  
                                <div className="divb-right">
                                    <p>Phiếu mua hàng 200,000đ áp dụng mua balo, túi chống sốc</p>
                                </div>
                            </div>
                            <div className="divb t3" >
                                <span className="nb">2.</span>
                                <div className="divb-right">
                                    <p>Giảm thêm 3% khi mua cùng sản phẩm có giá cao hơn (trừ Xe đạp, sản phẩm Apple, sản phẩm giá sốc)
                                    <a href="" target="_blank">(Xem chi tiết)</a>
                                    </p>
                                </div>
                            </div>
                            <div className="divb t3" >
                                <span className="nb">3.</span>
                                <div className="divb-right">
                                    <p>Hoàn 200,000đ cho chủ thẻ tín dụng HOME CREDIT khi thanh toán đơn hàng từ 5,000,000đ
                                         <a href="https://www.thegioididong.com/tin-tuc/hoan-tien-den-200K-khi-thanh-toan-qua-homecredit-1553498" target="_blank">(Xem chi tiết tại đây)</a>
                                    </p>
                                </div>
                            </div>
                            <div className="divb t3">
                                <span className="nb">4.</span>
                                <div className="divb-right">
                                    <p>Nhập mã MMSALE100 giảm ngay 1% tối đa 100.000đ khi thanh toán qua MOMO 
                                        <a href="https://www.thegioididong.com/tin-tuc/ctkm-giam-1-toi-da-100000d-khi-thanh-toan-momo-tren-website-tgdd-dmx-topzone-1551524" target="_blank">(Xem chi tiết tại đây)</a>
                                    </p>
                                </div>
                            </div>
                                </div>                       
                            <div className="pr-item text">
                                <p><span className="note">(*)</span> Giá hoặc khuyến mãi không áp dụng trả góp lãi suất đặc biệt (0%, 0.5%, 1%, 1.5%, 2%)</p>
                            </div>
                    </div>
             </div>

             <div className="purchase-button">
                <Link to="/cart">
                    <Button className="purchase-button-btn" varian="success" onClick={() => order(product)}>MUA NGAY</Button>
                </Link>
             </div>
            </Col>
        </Row>

        <div className="description">
            <div className="description-title">Mô tả sản phẩm:</div>
            <div className="description-content">{product.description}</div>
        </div>

        <hr />
        

        {user===null?<p>Vui lòng <Link to={url}>đăng nhập</Link> Viết đánh giá! </p>:<>
         <Button onClick={handleShowReviewModal} className="mt-2" variant="info">
            Viết đánh giá
          </Button>
          <Modal show={showReviewModal} onHide={handleCloseReviewModal}>
            <Modal.Header closeButton>
                <Modal.Title className="preview-title">Đánh giá sản phẩm</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <div>
                <p className="preview-product-title">{product.name}</p>
              </div>
              <Rating className="preview-rating" rating={rating} onRatingChange={handleRatingChange} />
              <Form.Control
                as="textarea"
                aria-label="With textarea"
                value={content}
                onChange={(e) => setContent(e.target.value)}
                placeholder="Chia sẻ cảm nhận"
              />
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleCloseReviewModal}>
                Hủy
              </Button>
              <Button variant="primary" onClick={addComment}>
                Đánh giá
              </Button>
            </Modal.Footer>
          </Modal>
        </>}
        <hr />
        <ListGroup>
        {Array.isArray(preview) && preview.map(c => (
            <div className="comment">
                 <ListGroup.Item id={c.id}>
                    {c.user && c.user.firstName} - <Moment locale="vi" fromNow>{c.createdDate}</Moment>
                    <CommentRating rating={c.preview} />
                    {c.comment && <div>{c.comment.content}</div>}
                </ListGroup.Item>
            </div>
          ))}
        </ListGroup>
    </>
}

export default ProductDetails;