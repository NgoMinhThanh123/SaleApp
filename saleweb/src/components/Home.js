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
    const [products, setProducts] = useState([]);
    const [numberSales, setNumberSales] = useState(null);
    const [salesData, setSalesData] = useState({});
    const [q] = useSearchParams();
    const [visibleProductCount, setVisibleProductCount] = useState(20);
    const productsPerPage = 20;

    const [visible, setVisible] = useState(false) 
  
    const toggleVisible = () => { 
      const scrolled = document.documentElement.scrollTop; 
      if (scrolled > 300){ 
        setVisible(true) 
      }  
      else if (scrolled <= 300){ 
        setVisible(false) 
      } 
    }; 
    
    const scrollToTop = () =>{ 
      window.scrollTo({ 
        top: 0,  
        behavior: 'smooth'
        /* you can also use 'auto' behaviour 
           in place of 'smooth' */
      }); 
    }; 
    
    window.addEventListener('scroll', toggleVisible); 

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
            let pro = res.data;

             // Lặp qua từng sản phẩm để gọi API lấy số lượng bán
             const salesPromises = res.data.map((product) => {
                return Apis.get(endpoints['numberOfSale'](product.id));
            });

            // Chạy tất cả các promise và lấy số lượng bán tương ứng
            const salesDataArray = await Promise.all(salesPromises);

            // Tạo một đối tượng với id sản phẩm và số lượng bán tương ứng
            const salesDataObject = {};
            salesDataArray.forEach((sales, index) => {
                salesDataObject[pro[index].id] = sales.data;
            });

            setSalesData(salesDataObject);
            console.log(salesDataObject);

           } catch (ex) {
               console.error(ex);
           }
        }

        loadProducts();
    }, [q]); 


    // const loadNumberOfSale = async () => {
    //     let {data} = await Apis.get(endpoints['numberOfSale'](products.id));
    //     setNumberSales(data);
    //     console.log(data);

    // }

    // loadNumberOfSale();


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
            <div className="button-contact">
                <Link to="https://www.facebook.com/thegioididongcom">
                    <Button variant="primary" className="facebook-button">
                        <i className="fab fa-facebook-f"></i>
                    </Button>
                </Link>
                <Link to="tel:0354472852">
                <Button variant="danger" className="hotline-button">
                    <i className="fas fa-phone-alt"></i>
                </Button>
                </Link>
                <Button
                        variant="info"
                        className="go-to-top-button"
                        onClick={scrollToTop}
                        style={{display: visible ? 'inline' : 'none'}}
                    >
                        <i className="fas fa-arrow-up"></i>
                </Button>                
            </div>
            <Row>
            <ul className="list-product">

                {products.slice(0, visibleProductCount).map((p) => {
                    let url = `/products/${p.id}`;
                    return <>
                        <li>
                            <Card style={{ width: '100%', padding: "5px"}} className="custom-card">
                            <Link to={url} className="detail">
                                <Card.Img style={{ width: '100%', height: '16rem'}}  variant="top" src={p.image} fluid="true" className="rounded" />
                            </Link>
                                <Card.Body>
                                <Link to={url} className="detail">
                                    <Card.Title className="custom-card-title">{p.name}</Card.Title>
                                    <Card.Text className="custom-card-price">{formatPrice(p.price)}</Card.Text>
                                </Link>
                                    <div className="button-container">
                                    <Button className="btn-order" onClick={() => order(p)}>
                                    <i className="fas fa-shopping-cart"></i> Đặt hàng
                                    </Button>
                                    <div className="number-sales">{salesData[p.id]?.numberSales} lượt bán</div>
                                    </div>
                                </Card.Body>
                            </Card>
                        </li>
                    
                    </>;
                })}
            </ul>
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