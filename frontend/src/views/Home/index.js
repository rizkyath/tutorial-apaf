import React from "react";
import listItems from "../../items.json";
import List from "../../components/List/index";
import "./index.css";

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

    render() {
        return (
            <div className="containter-fluid">
                <h1 className="text-center mt-3 mb-0"> Mini Commerce</h1>
                <p className="text-center text-secondary text-sm font-italic">
                    (this is a <strong>class-based</strong> application)
                </p>
                <div className="container pt-3">
                    <div className="row mt-3">
                        <div className="col-sm">
                            <List
                                title="List Items"
                                items={listItems}
                                onItemClick={() => {}}
                            ></List>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    handleAddItemToCart = (item) => {
        const newItems = [...this.state.cartItems];
        const newItem = {...item};
        const targetInd = newItems.findIndex((it) => it.id === newItem.id);

        if (targetInd < 0) {
            newItem.inCart = true;
            newItems.push(newItem);
            this.updateShopItem(newItem,true);
        }
        this.setState({ cartItems: newItems });
    };

    updateShopItem = (item, inCart) => {
        const tempShopItems = this.state.shopItems;
        const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
        tempShopItems[targetInd].inCart = inCart;
        this.setState({ shopItems: tempShopItems });
    };
}