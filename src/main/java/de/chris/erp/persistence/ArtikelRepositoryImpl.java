package de.chris.erp.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ArtikelRepositoryImpl implements ArtikelRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Artikel> sucheArtikelNachEigenschaften(Artikel artikel)
    {
        String abfrage = "from Artikel a ";
        Map<String,Object> eigenschaften = new HashMap<>();

        if(null != artikel.getNummer())
        {
            eigenschaften.put("nummer",artikel.getNummer());
        }
        if(null != artikel.getBezeichnung())
        {
            eigenschaften.put("bezeichnung",String.format("'%s'",artikel.getBezeichnung()));
        }

        if(!eigenschaften.isEmpty())
        {
            abfrage += " where ";

             abfrage += eigenschaften.entrySet()
                    .stream()
                    .map(entry -> "a." + entry.getKey() + " = " + entry.getValue())
                    .collect(Collectors.joining(" and "));
        }

        return entityManager.createQuery(abfrage).getResultList();
    }
}
