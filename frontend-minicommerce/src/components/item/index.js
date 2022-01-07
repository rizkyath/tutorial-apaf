import React, { Children } from "react";
import Button from "../button";
import classes from "./styles.module.css";

const Item = (props) => {
    const { id, title, price, description, category, quantity, handleEdit, handleDelete, children, handleAddToCart } = props;
    
    return (
        <div className={classes.item}>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <p>{`Stok: ${quantity}`}</p>
            <Button action={handleEdit}>
                Edit
            </Button>
            <Button action={handleDelete}>
                Delete
            </Button><br></br>
            {children}  
            <Button action={handleAddToCart}>
                Add To Cart
            </Button>
        </div>
    );


};
export default Item;