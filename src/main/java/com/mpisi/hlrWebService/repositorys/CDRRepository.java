package com.mpisi.hlrWebService.repositorys;

import com.mpisi.hlrWebService.entitys.Cdr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDRRepository extends JpaRepository<Cdr, Long> {
}
