package com.odvp.biblioteca.ControladoresVistas.DebtScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.Servicios.ServicioIconos;

public class HeaderDeudas extends HeaderDefault {
    private ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();
    private ButtonDefault buttonView = ButtonDefault.getButtonView();
    private DefaultSearcher searcher = DefaultSearcher.getDynamicSearcher(ServicioIconos.OPCION_MODULO_DEUDAS);
    public HeaderDeudas(ModeloDeuda modelo) {
        super("Biblioteca Eben Ezer");

    }
}
