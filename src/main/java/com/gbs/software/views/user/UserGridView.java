package com.gbs.software.views.user;

import com.gbs.software.backend.controllers.UserController;
import com.gbs.software.model.dto.UserDTO;
import com.gbs.software.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.ArrayList;
import java.util.List;


@PageTitle("DashBoard User")
@Route(value = "userGrid", layout = MainLayout.class)
@RouteAlias(value = "userGrid", layout = MainLayout.class)
public class UserGridView extends FlexLayout {

    final transient UserController controller;
    Grid<UserDTO> grid = new Grid<>(UserDTO.class);
    transient List<UserDTO> dtoList = new ArrayList<>();

    public UserGridView(UserController controller) {
        this.controller = controller;
        setContent();
    }

    private void setContent() {
        add(createGrid());
    }

    private Component createGrid() {

        dtoList = controller.findAll();

        grid.removeAllColumns();
        grid.addColumn("id").setHeader("ID").setFlexGrow(1);
        grid.addColumn("name").setHeader("Nome").setFlexGrow(1);
        grid.addColumn("email").setHeader("Email").setFlexGrow(1);
        grid.setItems(dtoList);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addItemClickListener(event -> showDetail(event.getItem()));

        grid.addItemDoubleClickListener(event -> new ConfirmDialog("Deletar Registro", "Deseja realmente deletar: " + event.getItem().getName() + " ?", "Sim", confirmEvent -> confirmaCancelamento(event.getItem()), "Cancelar", cancelEvent -> cancelEvent.getSource().close()).open());

        return grid;
    }

    private void showDetail(UserDTO item) {

        grid.setItemDetailsRenderer(new ComponentRenderer<>(() -> createDetail(item)));

    }

    private UserGridDetailView createDetail(UserDTO dto) {

        UserGridDetailView userGridDetailView = new UserGridDetailView();
        UserDTO userDTO = controller.findById(dto.getId());

        userGridDetailView.setDetail(userDTO);

        return userGridDetailView;
    }

    private void confirmaCancelamento(UserDTO item) {
        controller.deletar(item.getId());
        UI.getCurrent().access(this::refreshGrid);
    }

    private void refreshGrid() {
        dtoList = controller.findAll();
        grid.getDataProvider().refreshAll();
        grid.setItems(dtoList);
    }

}
