package com.crud.ui.demo;

import com.crud.ui.demo.dashboard.DashboardView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Minus.Vertical;

import org.springframework.util.StringUtils;

@Layout
public class MainView extends AppLayout {

    public MainView() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("My App");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        DrawerToggle toggle = new DrawerToggle();

        HorizontalLayout header = new HorizontalLayout(toggle, logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassName("header");

        addToNavbar(header);
    }

    // Navbar components can be used im a drawer layout
    private void createDrawer() {
      

        SideNav nav = new SideNav();

        SideNavItem dashboardLink = new SideNavItem(
            
        "Dashboard",
        "DashboardView",  
        VaadinIcon.DASHBOARD.create()
        );

        SideNavItem editOrders = new SideNavItem(

        "Edit orders",
        "EditOrdersView",
        VaadinIcon.EDIT.create()
        );
    

  
        nav.addItem(dashboardLink);
        nav.addItem(editOrders);
       
        addToDrawer(nav);
    }

    private RouterLink createNavItem(String text, Class<? extends Component> navigationTarget, VaadinIcon icon) {
        Icon itemIcon = icon.create();
        RouterLink link = new RouterLink();
        link.add(itemIcon, new Span(text));
        link.setRoute(navigationTarget); // ✅ Pass class here
        link.setTabIndex(-1);

        return link;

    }

}