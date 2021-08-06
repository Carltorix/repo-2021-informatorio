package com.informatorio.CarritoCompra.Repository;
import com.informatorio.CarritoCompra.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
}
