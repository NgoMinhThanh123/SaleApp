
import { useContext, useEffect, useState } from "react";
import { Button, Col, Form, Image, ListGroup, Row } from "react-bootstrap";
import Moment from "react-moment";
import { Link, useParams } from "react-router-dom";
import { MyUserContext } from "../App";
import Apis, { authApi, endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import "./ProductDetails.css"

const ProductDetails = () => {
    const [user, ] = useContext(MyUserContext);
    const {productId} = useParams();
    const [product, setProduct] = useState(null);
    const [comments, setComments] = useState(null);
    const [content, setContent] = useState();

    useEffect(() => {
        const loadProduct = async () => {
            let {data} = await Apis.get(endpoints['details'](productId));
            setProduct(data); 
        }

        const loadComments = async () => {
            let {data} = await Apis.get(endpoints['comments'](productId));
            setComments(data);
        }

        loadProduct();
        loadComments();
    }, []);

    const addComment = () => {
        const process = async () => {
            let {data} = await authApi().post(endpoints['add-comment'], {
                "content": content, 
                "product": product
            });

            setComments([...comments, data]);
            setContent("");
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
        <h1 className="text-center text-info mt-2">CHI TIẾT SẢN PHẨM ({productId})</h1>
        <Row>
            <Col className="col-product-image" md={6} xs={6}>
                <Image className="product-image" src={product.image} rounded fluid />
            </Col>
            <Col md={5} xs={6}>
                <h2 className="product product-title">{product.name}</h2>
                <h3 className="product product-price">Giá: {formatPrice(product.price)}</h3>
                <p>Mô tả:</p>
                <p>{product.description}</p>
            </Col>
        </Row>
        <hr />
        

        {user===null?<p>Vui lòng <Link to={url}>đăng nhập</Link> để bình luận! </p>:<>
        <Form.Control as="textarea" aria-label="With textarea" value={content} onChange={e => setContent(e.target.value)} placeholder="Nội dung bình luận" />
        <Button onClick={addComment} className="mt-2" variant="info">Bình luận</Button>
        </>}
        <hr />
        <ListGroup>
            {comments.map(c =>
            <div className="comment">
                 <ListGroup.Item id={c.id}>
                        {c.user.username} - <Moment locale="vi" fromNow>{c.createdDate}</Moment>
                        <div>{c.content} </div>
                </ListGroup.Item>
            </div>
                    )
            }
        </ListGroup>
    </>
}

export default ProductDetails;