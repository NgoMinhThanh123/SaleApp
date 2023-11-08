import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "categories": `${SERVER_CONTEXT}/api/categories/`,
    "products": `${SERVER_CONTEXT}/api/products/`,
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "register": `${SERVER_CONTEXT}/api/register/`,
    "pay": `${SERVER_CONTEXT}/api/pay/`,
    "details": (productId) => `${SERVER_CONTEXT}/api/products/${productId}/`,
    "comments": (productId) => `${SERVER_CONTEXT}/api/products/${productId}/comments/`,
    "add-comment": `${SERVER_CONTEXT}/api/comments/`,
    "previews": (productId) => `${SERVER_CONTEXT}/api/products/${productId}/previews/`,
    "add-preview": `${SERVER_CONTEXT}/api/previews/`,
    "preview-product": (productId) =>`${SERVER_CONTEXT}/api/previews/${productId}/`,
    "numberOfSale": (productId) =>`${SERVER_CONTEXT}/api/oder-detail/${productId}/`,
}

export const authApi = () => {
    const token = cookie.load("token");
    
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization":  `Bearer ${token}`
        }
    });
}

export default axios.create({
    baseURL: SERVER
})