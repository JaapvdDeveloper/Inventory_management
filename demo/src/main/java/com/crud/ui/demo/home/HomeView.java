package com.crud.ui.demo.home;

import com.crud.ui.demo.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;

@Route(value = "home", layout = MainView.class)
public class HomeView extends Div {


    public HomeView() {
        add(new H2("Welcome!"));
    }
    
}
