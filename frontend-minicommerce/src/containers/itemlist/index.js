import React, { Component } from "react";
import Item from "../../components/item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { Fab } from "@material-ui/core";
import Badge from "@material-ui/core/Badge";

class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [], cartItems: 0,
            isLoading: false,
            isCreate: false,
            isEdit: false,
            cartHidden: true,
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0,
            cartQty: 0
        };
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleEditItem = this.handleEditItem.bind(this);
        this.handleDeleteItem = this.handleDeleteItem.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField = this.handleChangeField.bind(this);
        this.handleSubmitItem = this.handleSubmitItem.bind(this);
        this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
        this.handleAddToCart = this.handleAddToCart.bind(this);
        this.filterData = this.filterData.bind(this);

    }
    handleAddItem() {
        this.setState({ isCreate:true });
    }
    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        });
    }
    async handleDeleteItem(item) {
        try {
            const data = {
                title: item.title,
                price: item.price,
                description: item.description,
                category: item.category,
                quantity: item.quantity
            };
            console.log(item.id);
            APIConfig.delete(`/item/${item.id}`);
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate:false, isEdit:false });
    }
    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
        console.log(this.state.cartQty);
    }
    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            console.log(data);
            await APIConfig.post("/item", data);
            this.setState({
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }
    componentDidMount() {
        this.loadData();
    }
    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
            this.setState({
                id: "",
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }
    async loadData() {
        try {
            const { data } = await APIConfig.get("/item");
            console.log(data);
            this.setState({ items: data.result });
            const cartData = await APIConfig.get("/cart");
            this.setState({ cartItems: cartData.data.result.length });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    async filterData(event) {
        try {
            const { data } = await APIConfig.get("/item");
            const { name, value } = event.target;
            const searchText = value;
            const dataAll = data.result;
            const filteredData = dataAll.filter((item) => item.title.includes(searchText));
            console.log(filteredData);
            this.setState({ items: filteredData });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    }
    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }
    async handleAddToCart(item) {
        
        try {
            const stok = item.quantity;
            const qty = parseInt(this.state.cartQty);
            if (qty === 0) {
                alert("Masukkan jumlah quantity");
                return;
            }
            else if (qty > stok) {
                alert("Stok tidak cukup!");
                return;
            }
            const data = {
                idItem: item.id,
                quantity: qty
            };
            console.log(data);
            await APIConfig.post("/cart", data);
            this.setState({
                cartQty: 0
            });
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        
    }
    render() {
        return (
            <div className={classes.itemList}>
                <h1 className={classes.title}>All Items</h1>
                <div style={{ position: "fixed", top: 25, right: 25 }}>
                    <Fab variant="extended" onClick={this.handleToggle} href="/mycart">
                        <Badge 
                            color="secondary"
                            badgeContent={this.state.cartItems}>
                            <ShoppingCartIcon />
                        </Badge>
                    </Fab>
                </div>
                <div class="input-group mb-3">
                    <input 
                        className={classes.textField}
                        type="text" 
                        name="searchText"
                        placeholder="Search.."
                        value={this.state.searchText}
                        onChange={this.filterData}
                    />
                </div>
                <Button action={this.handleAddItem}>
                    Create
                </Button>
                <div>
                    {this.state.items.map((item) => (
                        <Item
                            key={item.id}
                            id={item.id}
                            title={item.title}
                            price={item.price}
                            description={item.description}
                            category={item.category}
                            quantity={item.quantity}
                            handleEdit={() => this.handleEditItem(item)}
                            handleDelete={() => this.handleDeleteItem(item)}
                            handleAddToCart={() => this.handleAddToCart(item)}
                        >
                            <input
                                type="number"
                                min="0"
                                placeholder="qty"
                                name="cartQty"
                                onChange={this.handleChangeField}
                            />
                        </Item>
                    ))}
                </div>
                <Modal
                    show={this.state.isCreate || this.state.isEdit}
                    handleCloseModal={this.handleCancel}
                    modalTitle={this.state.isCreate
                        ? "Add Item"
                        : 'Edit Item ID ${this.state.id}'}
                >
                    <form>
                        <input
                            className={classes.textField}
                            type="text"
                            placeholder="Nama Item"
                            name="title"
                            value={this.state.title}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="number"
                            placeholder="Harga"
                            name="price"
                            value={this.state.price}
                            onChange={this.handleChangeField}
                        />
                        <textarea
                            className={classes.textField}
                            placeholder="Deskripsi"
                            name="description"
                            rows="4"
                            value={this.state.description}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="text"
                            placeholder="Kategori"
                            name="category"
                            value={this.state.category}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="number"
                            placeholder="qty"
                            name="quantity"
                            value={this.state.quantity}
                            onChange={this.handleChangeField}
                        />
                        <Button action={this.state.isCreate 
                            ? this.handleSubmitItem
                            : this.handleSubmitEditItem}>
                            Submit
                        </Button>
                        <Button action={this.handleCancel}>
                            Cancel
                        </Button>
                    </form>
                </Modal>
            </div>
        );
    }
}
export default ItemList;
