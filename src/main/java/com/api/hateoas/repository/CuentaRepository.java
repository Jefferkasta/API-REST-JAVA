package com.api.hateoas.repository;

import com.api.hateoas.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Integer> {
    @Query("UPDATE Cuenta c SET c.monto=c.monto + ?1 WHERE c.id=?2")
    @Modifying
    void actualizarMonto( float monto, Integer id);

    Optional<Cuenta> findByNumeroDeCuenta(String numeroDeCuenta);
}
