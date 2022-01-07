import React, { Children } from "react";
import classes from "./styles.module.css";

const CartItem = (props) => {
    const { id, title, price, description, category, totalPrice } = props;
    
    return (
        <div className={classes.item}>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <p><b>{`Price: ${totalPrice}`}</b></p>
        </div>
    );

};
export default CartItem;