import React from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import ItemList from './containers/itemlist';
import Cart from './containers/cart';

export const AppRoutes = () => {
    return (
        <div>
            <Switch>
                <Route exact path="/items" component={ItemList} />
                <Route exact path="/mycart" component={Cart} />
                <Route exact path="/">
                    <Redirect to="/items" />
                </Route>
            </Switch>
        </div>
    );
};