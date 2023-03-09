package com.gbs.software.views.user;

import com.gbs.software.backend.controllers.UserController;
import com.gbs.software.exceptions.DatabaseException;
import com.gbs.software.model.dto.UserDTO;
import com.gbs.software.model.entities.User;
import com.gbs.software.utils.UIUtils;
import com.gbs.software.views.MainLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;



@PageTitle("User")
@Route(value = "user", layout = MainLayout.class)
@RouteAlias(value = "user", layout = MainLayout.class)
public class UserView extends FlexLayout {

    TextField txtNome = new TextField("Nome:", "Digite o nome");
    TextField txtEmail = new TextField("Email: ", "Digite o email");
    TextArea txtBiogragia = new TextArea("Biografia", "Digite aqui sua biografia");
    TextField txtGithub = new TextField("GitHub: ", "Digite o endereço do seu github");
    Binder<User> binderUser = new Binder<>(User.class);
    private final transient  UserController userController;
    private static final String CAMPO_OBRIGATORIO = "Campos Obrigatório";
    transient User user;


    public UserView(UserController userController) {
        this.userController = userController;
        setContent();
    }

    private void setContent() {
        setWidthFull();
        setHeightFull();
        getStyle().set("width", "-webkit-fill-available");
        getStyle().set(LumoUtility.Margin.Vertical.LARGE, LumoUtility.Padding.Horizontal.LARGE);
        setAlignItems(Alignment.STRETCH);
        setFlexDirection(FlexDirection.COLUMN);
        add(registerCard());
    }

    private Component registerCard() {
        FormLayout formLayout = new FormLayout();

        binderUser.forField(txtNome).asRequired(CAMPO_OBRIGATORIO).bind(User::getName, User::setName);
        binderUser.forField(txtEmail).asRequired(CAMPO_OBRIGATORIO).bind(User::getEmail, User::setEmail);
        binderUser.forField(txtBiogragia).bind(User::getBiografia, User::setBiografia);
        binderUser.forField(txtGithub).bind(User::getGithub, User::setGithub);

        Button btnRegistrar = UIUtils.createPrimaryButton("Registrar");
        btnRegistrar.addClickListener(this::registrarUsuario);


        formLayout.add(txtNome, txtEmail, txtBiogragia, txtGithub, btnRegistrar);

        formLayout.setColspan(txtNome, 1);
        formLayout.setColspan(txtEmail, 1);
        formLayout.setColspan(txtBiogragia, 2);
        formLayout.setColspan(txtGithub, 2);

        return formLayout;
    }

    private void registrarUsuario(ClickEvent<Button> buttonClickEvent) {

        if (!binderUser.validate().isOk()) {
            Notification.show("Favor preencher todos campos obrigatórios");
        }

        try{
            user = new User();
            binderUser.writeBean(user);

            UserDTO dto = user.toDTO();
            userController.registrar(dto);

            Notification.show("Dados salvos com sucesso !");

        }catch (Exception e){
            Notification.show("Erro ao registrar Usuário: " +e.getMessage());
            throw new DatabaseException("Erro ao inserir dados: "+e.getMessage());
        }




    }


}
