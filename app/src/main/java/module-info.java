module mainModule {
    requires transitive java.desktop;
    requires transitive java.sql;

    exports com.unoapp.uno;
    exports com.unoapp.uno.engine;
    exports com.unoapp.uno.models;
    exports com.unoapp.uno.ui.components;
    exports com.unoapp.uno.ui.drawables;
    exports com.unoapp.uno.ui.screens;
    exports com.unoapp.uno.utils;
}
