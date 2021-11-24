import React from "react";
import listItems from "../../items.json";
import List from "../../components/List/index";
import "./index.css";
import { Fab } from "@material-ui/core";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import ViewStreamIcon from "@mui/icons-material/ViewStream";

export default class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shopItems: listItems,
            cartItems: [],
            cartHidden: true,
            balance: 120
        };
    }

    // render() {
    //     return (
    //         <div className="containter-fluid">
    //             <h1 className="text-center mt-3 mb-0"> Mini Commerce</h1>
    //             <p className="text-center text-secondary text-sm font-italic">
    //                 (this is a <strong>class-based</strong> application)
    //             </p>
    //             <div className="container pt-3">
    //                 <div className="row mt-3">
    //                     <div className="col-sm">
    //                         <List
    //                             title="List Items"
    //                             items={listItems}
    //                             onItemClick={() => {}}
    //                         ></List>
    //                     </div>
    //                 </div>
    //             </div>
    //         </div>
    //     );
    // }

    render() {
        return (
            <div className="container-fluid">
                <h1 className="text-center mt-3 mb-0">Mini Commerce</h1>
                <div style={{ position: "fixed", top:25, right:25}}>
                    <Fab variant="extended" onClick={this.handleToggle}>
                        {this.state.cartHidden ?
                            <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                                <ShoppingCartIcon />
                            </Badge>
                            : <ViewStreamIcon/>}
                    </Fab>
                </div>
                <p className="text-center text-secondary text-sm font-italic">
                    (this is a <strong>class-based</strong> application)
                </p>
                <p className="text-center text-primary"> Your Balance: <b>{this.state.balance}</b></p>
                <div className="container pt-3">
                    <div className="row mt-3">
                        {!this.state.cartHidden ? (
                            <div className="col-sm">
                                <List
                                    title="My Cart"
                                    items={this.state.cartItems}
                                    onItemClick={this.handleDeleteItemFromCart}
                                ></List>
                            </div>
                        ) : <div className="col-sm">
                            <List
                                title="List Items"
                                items={this.state.shopItems}
                                onItemClick={this.handleAddItemToCart}
                                isShopList={true}
                            ></List>
                            </div>}
                    </div>
                </div>
            </div>
        );
    };


    handleAddItemToCart = (item) => {
        const newItems = [...this.state.cartItems];
        const balance = [this.state.balance];
        const newItem = {...item};
        const newItemPrice = newItem.price;
        const targetInd = newItems.findIndex((it) => it.id === newItem.id);

        const newBalance = balance - newItemPrice;
        if (newBalance < 0) {
            alert("Balance not sufficient");
        } else {
            if (targetInd < 0) {
                newItem.inCart = true;
                newItems.push(newItem);
                this.updateShopItem(newItem,true);
            }
            this.setState({ cartItems: newItems });
            this.setState({ balance: parseFloat(newBalance.toFixed(2)) });
        }
    };

    handleDeleteItemFromCart = (item) => {
        const newItems = [...this.state.cartItems];
        const itemDelete = {...item};
        const balance = [this.state.balance];
        const itemDeletePrice = itemDelete.price.toFixed(2);

        const newBalance = parseFloat(balance) + parseFloat(itemDeletePrice);
        
        itemDelete.inCart = false;
        this.updateShopItem(itemDelete, false);
        this.setState({cartItems: newItems.filter((it) => it.id != itemDelete.id)});
        this.setState({ balance: newBalance });
    }

    updateShopItem = (item, inCart) => {
        const tempShopItems = this.state.shopItems;
        const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
        tempShopItems[targetInd].inCart = inCart;
        this.setState({ shopItems: tempShopItems });
    };

    handleToggle = () => {
        const cartHidden = this.state.cartHidden;
        this.setState({ cartHidden: !cartHidden });
    };


}