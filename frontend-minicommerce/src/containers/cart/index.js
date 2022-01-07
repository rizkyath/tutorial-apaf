import React, { Component } from "react";
import CartItem from "../../components/cartitem";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";

class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            cartItems: [],
        };
        this.handleCheckout = this.handleCheckout.bind(this);
    }
    async loadCart() {
        try {
            const cartData = await APIConfig.get("/cart");
            this.setState({ cartItems: cartData.data.result });
            console.log(cartData.data.result);
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    componentDidMount() {
        this.loadCart();
    }
    calculateTotalPrice(item) {
        const cartQty = item.quantity;
        const price = item.item.price;
        const totalPrice = cartQty * price;
        return totalPrice;
    }
    async handleCheckout() {
        await APIConfig.get("/cart/checkout");
        this.loadCart();
    }
    render() {
        return (
            <div className={classes.itemList}>
                <div style={{ position: "fixed", top: 25, left: 25 }}>
                    <Button
                    ><a href="/items">Back</a>
                    </Button>
                </div>
                <h1 className={classes.title}>Cart Items</h1>
                <div style={{ position: "fixed", top: 25, right: 25 }}>
                    <Button
                        action={this.handleCheckout}>Checkout</Button>
                </div>
                <div>
                    {this.state.cartItems.map((item) => (
                        <CartItem
                            key={item.item.id}
                            id={item.item.id}
                            title={item.item.title}
                            price={item.item.price}
                            description={item.item.description}
                            category={item.item.category}
                            totalPrice={this.calculateTotalPrice(item)}
                        />
                    ))}
                </div>
            </div>
        );
    }
}
export default Cart;