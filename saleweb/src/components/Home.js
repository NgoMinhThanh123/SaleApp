import { useContext, useEffect, useState } from "react"
import { Alert, Button, Card, Carousel, Col, Pagination, Row } from "react-bootstrap";
import cookie from "react-cookies";
import { Link, useSearchParams } from "react-router-dom";
import { MyCartContext } from "../App";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import "./Home.css"

const Home = () => {
    const [, cartDispatch] = useContext(MyCartContext);
    const [products, setProducts] = useState(null);
    const [codeStatus, setCodeStatus] = useState(null);
    const [q] = useSearchParams();
    const [visibleProductCount, setVisibleProductCount] = useState(12);
    const productsPerPage = 12;

    useEffect(() => {
        const loadProducts = async () => {
           try {
            let e = endpoints['products'];

            let cateId = q.get("cateId");
            if (cateId !== null)
                e = `${e}?cateId=${cateId}`;
            else {
                let kw = q.get("kw");
                if (kw !== null)
                    e = `${e}?kw=${kw}`;
            }
            
            let res = await Apis.get(e);
            setProducts(res.data);
           } catch (ex) {
               console.error(ex);
           }
        }

        loadProducts();
    }, [q]); 

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

    const loadMoreProducts = () => {
        setVisibleProductCount((prevCount) => prevCount + productsPerPage);
    };


    if (products === null) 
        return <MySpinner />

    if (products.length === 0)
        return <Alert variant="info" className="mt-1">Không có sản phẩm nào!</Alert>

    function formatPrice(price) {
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
    }

    return (
        <>
        <div className="home-carousel">
                <Carousel interval={3000} data-bs-theme="dark">
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://cdn.sforum.vn/sforum/wp-content/uploads/2020/01/Screen-Shot-2020-01-02-at-17.31.10.png"
                            alt="First slide"
                            style={{ height: "400px" }}
                            fluid="true"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://cdn.hoanghamobile.com/tin-tuc/wp-content/uploads/2018/12/Samsung-khuyen-mai-Tet.png"
                            alt="Second slide"
                            style={{ height: "400px" }}
                            fluid="true"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://fptshop.com.vn/Uploads/Originals/2019/1/4/636822212752243691_samsung-kmt-tet.jpg"
                            alt="Third slide"
                            style={{ height: "400px" }}
                            fluid="true"
                        />
                    </Carousel.Item>
                </Carousel>
            </div>
            <Row>
                {products.slice(0, visibleProductCount).map((p) => {
                    let url = `/products/${p.id}`;
                    return <>
                        <Col xs={12} md={3} className="mt-2 mb-2" key={p.id}>
                            <Card style={{ width: '18rem'}}>
                            <Link to={url} className="detail">
                                <Card.Img style={{ width: '100%', height: '15rem'}}  variant="top" src={p.image} fluid="true" className="rounded" />
                            </Link>
                                <Card.Body>
                                <Link to={url} className="detail">
                                    <Card.Title className="custom-card-title">{p.name}</Card.Title>
                                    <Card.Text className="custom-card-price">{formatPrice(p.price)}</Card.Text>
                                </Link>
                                    <div className="button-container">
                                    <Button variant="success" onClick={() => order(p)}>
                                    <i className="fas fa-shopping-cart"></i> Đặt hàng
                                    </Button>
                                    </div>
                                </Card.Body>
                            </Card>
                        </Col>
                        
                    </>;
                })}
            </Row>
            {visibleProductCount < products.length && (
                <div className="text-center mt-3">
                    <Button variant="primary" onClick={loadMoreProducts}>Xem thêm</Button>
                </div>
            )}

        </>
    )
}

export default Home