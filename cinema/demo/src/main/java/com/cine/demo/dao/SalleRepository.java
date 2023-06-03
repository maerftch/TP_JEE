package com.cine.demo.dao;

import com.cine.demo.entities.Categorie;
import com.cine.demo.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface SalleRepository extends JpaRepository<Salle,Long> {
}
