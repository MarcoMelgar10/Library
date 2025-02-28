package com.odvp.biblioteca.postgresql.conexionPostgresql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
    private static ConexionDB instancia;
    private final HikariDataSource dataSource;

    private ConexionDB() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://ep-jolly-lab-a8wz5ags-pooler.eastus2.azure.neon.tech:5432/neondb");
        config.setUsername("neondb_owner");
        config.setPassword("npg_XgQ6PsVu3OtR");

        // Configuración del pool de conexiones
        config.setMaximumPoolSize(20);  // 20 conexiones simultáneas (ajustar según carga real)
        config.setMinimumIdle(2);       // Mantener 5 conexiones listas (evita latencia)
        config.setIdleTimeout(30000);   // 30s antes de cerrar conexiones inactivas
        config.setMaxLifetime(1800000); // 30 minutos de vida útil (correcto)
        config.setConnectionTimeout(5000); // 5s de timeout para obtener una conexión

        this.dataSource = new HikariDataSource(config);
    }

    public static ConexionDB getOrCreate() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public Connection getConexion() throws SQLException {
        System.out.println("conexion obtenida");
        return dataSource.getConnection(); // Obtiene una conexión del pool
    }

    public void cerrarPool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
